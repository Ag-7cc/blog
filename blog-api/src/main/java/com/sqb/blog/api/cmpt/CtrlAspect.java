package com.sqb.blog.api.cmpt;

import com.sqb.blog.api.utl.HttpRequestUtil;
import com.sqb.blog.api.utl.RequestContext;
import com.sqb.blog.api.view.View;
import com.sqb.blog.api.view.ViewUtil;
import com.sqb.blog.util.AppContext;
import com.sqb.blog.util.DateUtil;
import com.sqb.blog.util.JsonUtil;
import com.sqb.blog.util.exception.BizException;
import com.sqb.blog.util.respcode.RespCode;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Order(1)
public class CtrlAspect {
    private static final Logger log = LoggerFactory.getLogger(CtrlAspect.class);

    @Around("within(com.sqb.blog.api.ctrl.*) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    protected Object aroudAdivce(ProceedingJoinPoint jp) throws Throwable {
        // Log4JStopWatch stopWatch = new Log4JStopWatch();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            // 支持跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

            String reqUri = request.getServletPath();
            if (request.getQueryString() != null) {
                reqUri += "?" + request.getQueryString();
            }
            String reqMethod = request.getMethod().toUpperCase();
            String clientIp = HttpRequestUtil.getRealRemoteAddr(request);
            RequestContext.putClientIp(clientIp);
            RequestContext.putReqUri(reqUri);
            RequestContext.putReqMethod(reqMethod);

            String fixLogString = StringUtils.rightPad(reqMethod, 5, ' ') + " " + reqUri;
            if (!"GET".equals(reqMethod)) {
                fixLogString += ": param=" + paramString(request);
            }
            log.debug("请求开始.......: {}", fixLogString);
            View rt = null;
            long start = System.currentTimeMillis();
            try {
                // == 用户获取 == //
                String token = request.getParameter("token");
                if (token != null) {
                    // FIXME 查义用户
                }

                // == 公用参数校验 == //
                Object timestampObj = request.getParameter("timestamp");
                Long timestamp;
                try {
                    timestamp = timestampObj == null ? null : timestampObj instanceof Long ? (Long) timestampObj : Long
                            .valueOf((String) timestampObj);
                } catch (Exception e) {
                    throw new BizException(RespCode.RES_0010, new Object[]{"timestamp", timestampObj});
                }
                if (timestamp == null) {
                    throw new BizException(RespCode.RES_0012, new Object[]{"timestamp"});
                }
                rt = (View) jp.proceed();
            } catch (Throwable t) {
                if (t instanceof BizException) {
                    BizException be = (BizException) t;
                    rt = ViewUtil.defaultView(be.getCode(), be.getArgs(), be.getMsg(), be.getOriginalCode(), be.getOrginalMsg());
                    if (be.getCause() != null) {
                        log.error("请求异常.......: " + fixLogString, t);
                    } else {
                        log.warn("请求异常.......: {}, BizException={}", fixLogString, t);
                    }
                } else {
                    rt = ViewUtil.defaultView(RespCode.RES_9999);
                    log.error("请求异常.......: " + fixLogString, t);
                }
            } finally {
                RequestContext.clear();
            }
            long cost = System.currentTimeMillis() - start;

            if (rt == null) {
                log.info("请求结束[{}]: {}, result={}", StringUtils.leftPad(cost + "", 5), fixLogString, null);
                return null;
            }
            rt = ViewUtil.builder(rt).timestamp(DateUtil.now()).build();
            if (log.isInfoEnabled()) {
                log.info("请求结束[{}]: {}, result={}", StringUtils.leftPad(cost + "", 5), fixLogString,
                        JsonUtil.toStringIncludeIgnoredProperties(rt, DateUtil.PATTERN_YEAR2MILLISECOND));
            }
            return rt;
        } finally {
            // stopWatch.stop("api_" + request.getRequestUri());
        }
    }

    protected String paramString(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            Object val = entry.getValue();
            if (entry.getValue() != null && entry.getValue().length == 1) {
                val = entry.getValue()[0];
            }
            if (AppContext.isEnvProduct() && AppContext.isPwdField(entry.getKey())) {
                paramMap.put(entry.getKey(), AppContext.getPwdMask());
            } else {
                paramMap.put(entry.getKey(), val);
            }
        }
        return JsonUtil.toString(paramMap, DateUtil.PATTERN_YEAR2MILLISECOND);
    }

}
