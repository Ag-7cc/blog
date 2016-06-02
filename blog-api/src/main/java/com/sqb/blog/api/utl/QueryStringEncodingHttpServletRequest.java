package com.sqb.blog.api.utl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;

import com.sqb.blog.api.utl.QueryStringDecoder;

public class QueryStringEncodingHttpServletRequest extends HttpServletRequestWrapper {

	Map<String, List<ValuePair>> needProParamMap;
	String queryString = null;

	public QueryStringEncodingHttpServletRequest(HttpServletRequest request, Charset from, Charset to) {
		super(request);

		queryString = request.getQueryString();
		if (queryString != null) {
			QueryStringDecoder decoder = new QueryStringDecoder(queryString, from, false);
			for (Entry<String, List<String>> entry : decoder.parameters().entrySet()) {
				for (String val : entry.getValue()) {
					String newVal = new String(val.getBytes(from), to);
					if (StringUtils.equals(val, newVal)) {
						continue;
					}
					putParam2Need(entry.getKey(), new ValuePair(val, newVal));
				}
			}
			try {
				queryString = URLDecoder.decode(queryString, to.name());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected void putParam2Need(String key, ValuePair vp) {
		if (needProParamMap == null) {
			needProParamMap = new HashMap<String, List<ValuePair>>();
		}
		List<ValuePair> vps = needProParamMap.get(key);
		if (vps == null) {
			vps = new LinkedList<ValuePair>();
			needProParamMap.put(key, vps);
		}
		vps.add(vp);
	}

	protected String transParam(String key, String val) {
		if (needProParamMap == null) {
			return val;
		}
		List<ValuePair> vps = needProParamMap.get(key);
		if (vps == null) {
			return val;
		}
		for (ValuePair vp : vps) {
			if (StringUtils.equals(vp.getOriVal(), val)) {
				return vp.getNewVal();
			}
		}
		return val;
	}

	@Override
	public String getParameter(String name) {
		String val = super.getParameter(name);
		return transParam(name, val);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = super.getParameterMap();
		for (Entry<String, String[]> entry : map.entrySet()) {
			String key = entry.getKey();
			String[] vals = entry.getValue();
			for (int i = 0; i < vals.length; i++) {
				vals[i] = transParam(key, vals[i]);
			}
		}
		return map;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] vals = super.getParameterValues(name);
		if (vals != null) {
			for (int i = 0; i < vals.length; i++) {
				vals[i] = transParam(name, vals[i]);
			}
		}
		return vals;
	}

	@Override
	public String getQueryString() {
		return queryString;
	}

	protected class ValuePair {
		String oriVal;
		String newVal;

		public ValuePair(String oriVal, String newVal) {
			super();
			this.oriVal = oriVal;
			this.newVal = newVal;
		}

		public String getOriVal() {
			return oriVal;
		}

		public String getNewVal() {
			return newVal;
		}

	}

}
