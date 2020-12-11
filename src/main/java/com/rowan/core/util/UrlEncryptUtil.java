package com.rowan.core.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhanghao
 * @date 2019/9/30 18:11
 **/
public class UrlEncryptUtil {
    public static String getParameterString(Map<?, ?> paramMap, String... excludedKey) {
        MapNumberKeyComparator<String> bvc = new MapNumberKeyComparator(MapNumberKeyComparator.Order.asc);
        TreeMap<String, Object> sortedMap = new TreeMap(bvc);
        sortedMap.putAll((Map<? extends String, ? extends Object>) paramMap);
        StringBuffer sb = new StringBuffer();
        Iterator iterator = sortedMap.keySet().iterator();

        while (true) {
            boolean isIgnore;
            String key;
            do {
                if (!iterator.hasNext()) {
                    if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '&') {
                        return sb.substring(0, sb.length() - 1);
                    }

                    return sb.toString();
                }

                isIgnore = false;
                key = (String) iterator.next();
                if (excludedKey != null) {
                    for (int i = 0; i < excludedKey.length; ++i) {
                        if (key.equalsIgnoreCase(excludedKey[i])) {
                            isIgnore = true;
                        }
                    }
                }
            } while (isIgnore);

            String[] values = ( String[])paramMap.get(key);
            String[] as = values;
            int j = values.length;

            for (int i = 0; i < j; ++i) {
                String value = as[i];
                sb.append(key).append("=").append(value).append("&");
            }
        }
    }
}
