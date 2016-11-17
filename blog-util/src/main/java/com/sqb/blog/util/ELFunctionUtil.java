package com.sqb.blog.util;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.io.OutputFormat;

import java.io.File;

/**
 * Created by vic.shan
 * Date: 2016/11/17.10:43
 */
public class ELFunctionUtil {
    /**
     * XML格式化，使之适应Html里的textarea
     */
    private static OutputFormat XML_OUTPUT_FORMAT_HTML;


    static {
        XML_OUTPUT_FORMAT_HTML = OutputFormat.createPrettyPrint();
        XML_OUTPUT_FORMAT_HTML.setIndentSize(4);
        XML_OUTPUT_FORMAT_HTML.setIndent("&nbsp;");
        XML_OUTPUT_FORMAT_HTML.setSuppressDeclaration(true);
    }

    /**
     * 格式化文件系统的后缀
     *
     * @param path   文件路径
     * @param suffix 后缀
     * @return 格式化后的文件路径
     */
    public static String fs(String path, String suffix) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        String filePath = String.format(path, suffix);
        return AppContext.getServerWebSite() + File.separator + filePath;
    }
}
