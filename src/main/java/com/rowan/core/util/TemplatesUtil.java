package com.rowan.core.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * thymeleaf模板
 * @author zhanghao
 * @date 2019/8/30 16:23
**/
public class TemplatesUtil {
    public static String createTemplates(Map<String, Object> dataMap, String templatesName, TemplateEngine templateEngine) {
        Context context = new Context();
        context.setVariables(dataMap);
        String emailText = templateEngine.process(templatesName, context);
        return emailText;
    }
}
