
package com.rowan.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class StringUtil extends StringUtils {
    static final Pattern mobilePattern = Pattern.compile("^1\\d{10}$");
    static final Pattern userNamePt = Pattern.compile("^lv[0-9]{11}$");
    static final Pattern pinyinPattern = Pattern.compile("([a-z|A-Z]*)");
    static final Pattern datePattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    static final Pattern emailPattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    static final Pattern phonePattern = Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$");
    private static final Log log = LogFactory.getLog(StringUtil.class);
    public static final int toLowerCase = 1;
    public static final int toUpperCase = 2;
    private static final String RANDOM_STRING_POOL = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    public StringUtil() {
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return "".equals(str.trim());
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        return "".equals(str.trim());
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static Boolean toBoolean(String value, boolean defaultValue) {
        if (isEmpty(value)) {
            return defaultValue;
        } else {
            try {
                return Boolean.parseBoolean(value);
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    public static Boolean toBoolean(String value) {
        return toBoolean(value, false);
    }

    public static Long toLong(String value, Long defaultValue) {
        if (isEmpty(value)) {
            return defaultValue;
        } else {
            try {
                return Long.parseLong(value);
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    public static Integer toInt(String value, Integer defaultValue) {
        if (isEmpty(value)) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(value);
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    public static Double toDouble(String value, Double defaultValue) {
        if (isEmpty(value)) {
            return defaultValue;
        } else {
            try {
                return Double.parseDouble(value);
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    public static Float toFloat(String value, Float defaultValue) {
        if (isEmpty(value)) {
            return defaultValue;
        } else {
            try {
                return Float.parseFloat(value);
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    public static String join(Object[] array) {
        return join(array, ",", "");
    }

    public static String join(Object[] array, String delimiter) {
        return join(array, delimiter, "");
    }

    public static String join(Object[] array, String delimiter, String surround) {
        if (array == null) {
            throw new IllegalArgumentException("Array can not be null");
        } else if (array.length == 0) {
            return "";
        } else {
            if (surround == null) {
                surround = "";
            }

            if (delimiter == null) {
                surround = ",";
            }

            StringBuffer buffer = new StringBuffer();
            Object[] var4 = array;
            int var5 = array.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Object item = var4[var6];
                buffer.append(surround).append(item.toString()).append(surround).append(delimiter);
            }

            buffer.delete(buffer.length() - delimiter.length(), buffer.length());
            return buffer.toString();
        }
    }

    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception var7) {
            return password;
        }

        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; ++i) {
            if ((encodedPassword[i] & 255) < 16) {
                buf.append("0");
            }

            buf.append(Long.toString((long) (encodedPassword[i] & 255), 16));
        }

        return buf.toString();
    }

    public static String getRandomString(int len) {
        Random rd = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < len; ++i) {
            int rdGet = Math.abs(rd.nextInt()) % 10 + 48;
            char ch = (char) rdGet;
            sb.append(ch);
        }

        return sb.toString();
    }

    public static String subStringStr(String str, int size) {
        if (isEmpty(str)) {
            return "";
        } else if (str.length() < size) {
            return str;
        } else {
            String lastString = str.substring(0, size) + "...";
            return lastString;
        }
    }

    public static String subStringBr(String str) {
        String regex = "\\u002B";
        if (isEmpty(str)) {
            return "";
        } else {
            String strBr = str.replaceAll(regex, "<br/>");
            return strBr;
        }
    }

    public static String leftPadAndSubString(String str, int size, String padStr) {
        String str2 = leftPad((String) str, size, padStr);
        str2 = substring(str2, 0, size);
        return str2;
    }

    public static String subStringStrNoSuffix(String str, int size) {
        if (isEmpty(str)) {
            return "";
        } else if (str.length() < size) {
            return str;
        } else {
            String lastString = str.substring(0, size);
            return lastString;
        }
    }

    public static boolean isEmptyString(String str) {
        return str == null ? true : str.trim().equals("");
    }

    public static boolean isNotEmptyString(String str) {
        return !isEmptyString(str);
    }

    public static String composeMessage(String template, Map<String, Object> data) {
        String regex = "\\$\\{(.+?)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);

        StringBuffer sb;
        String value;
        for (sb = new StringBuffer(); matcher.find(); matcher.appendReplacement(sb, value)) {
            String name = matcher.group(1);
            value = null == data.get(name) ? "" : data.get(name).toString();
            if (value == null) {
                value = "";
            } else {
                value = value.replaceAll("\\$", "\\\\\\$");
            }
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String getPercent(int n, Float f) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(n);
        String str = nf.format(f);
        return str;
    }

    public static String replaceNullStr(String str) {
        if (str == null) {
            return "";
        } else if (!isEmptyString(str)) {
            if ("null".equals(str)) {
                str = str.replace("null", "");
                return str;
            } else {
                return str;
            }
        } else {
            return str;
        }
    }

    public static String escapeSQLChar(String str) {
        return !isEmpty(str) ? str.replaceAll("'", "''") : null;
    }

    public static final boolean validMobileNumber(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        } else {
            return phonePattern.matcher(mobile).matches();
        }
    }

    public static boolean validEmail(String email) {
        if (isEmpty(email)) {
            return false;
        } else {
            return emailPattern.matcher(email).matches();
        }
    }

    public static boolean validUserName(String userName) {
        boolean isAllDigit = userName.matches("[0-9]+");
        return !isAllDigit;
    }

    public static final boolean validMembershipCard(String membershipCard) {
        return true;
    }

    public static String trimFirstAndLastChar(String source, char element) {
        boolean beginIndexFlag = true;
        boolean endIndexFlag = true;

        do {
            int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
            int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length();
            source = source.substring(beginIndex, endIndex);
            beginIndexFlag = source.indexOf(element) == 0;
            endIndexFlag = source.lastIndexOf(element) + 1 == source.length();
        } while (beginIndexFlag || endIndexFlag);

        return source;
    }

    public static List<String> splitToList(String str, String delim) {
        List<String> splitList = null;
        StringTokenizer st = null;
        if (str == null) {
            return splitList;
        } else {
            if (delim != null) {
                st = new StringTokenizer(str, delim);
            } else {
                st = new StringTokenizer(str);
            }

            if (st != null && st.hasMoreTokens()) {
                splitList = new ArrayList();

                while (st.hasMoreTokens()) {
                    splitList.add(st.nextToken());
                }
            }

            return splitList;
        }
    }

    public static String toUTF8(String str) {
        if (isNotEmpty(str)) {
            try {
                return new String(str.getBytes("iso8859-1"), "UTF-8");
            } catch (Exception var2) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String hiddenMobile(String source) {
        if (isBlank(source)) {
            return "";
        } else {
            return mobilePattern.matcher(source).matches() ? source.substring(0, 3) + "****" + source.substring(7, 11) : source;
        }
    }

    public static String hiddenEmail(String source) {
        if (isBlank(source)) {
            return "";
        } else {
            if (emailPattern.matcher(source).matches()) {
                int splitIndex = source.indexOf("@");
                String emailPrefix = source.substring(0, splitIndex);
                if (emailPrefix.length() > 4) {
                    source = source.substring(0, emailPrefix.length() - 4) + "****" + source.substring(splitIndex);
                } else {
                    source = source.substring(0, 1) + "****" + source.substring(splitIndex);
                }

                return source;
            } else {
                return source;
            }
        }
    }

    public static String hiddenIDCard(String source) {
        if (isBlank(source)) {
            return "";
        } else {
            if (source.length() > 8) {
                source = source.substring(0, source.length() - 8) + "********";
            } else {
                source = source.substring(0, 1) + "********";
            }

            return source;
        }
    }

    public static String printParam(Object object) {
        if (object == null) {
            return "";
        } else {
            StringBuffer out = new StringBuffer();

            try {
                Class<?> clazz = object.getClass();
                out.append(clazz.getSimpleName());
                out.append(" [");
                Method[] methods = clazz.getMethods();
                Method[] var4 = methods;
                int var5 = methods.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    Method method = var4[var6];
                    if (method.getName().indexOf("get") >= 0) {
                        Type[] types = method.getGenericParameterTypes();
                        if (types == null || types.length == 0) {
                            Method mtd = clazz.getMethod(method.getName());
                            Object value = mtd.invoke(object);
                            if (value != null) {
                                out.append(method.getName() + "=" + value.toString() + ",");
                            }
                        }
                    }
                }

                out.append("]");
            } catch (Exception var11) {
                ;
            }

            return out.toString();
        }
    }

    public static String buildStringByTemplate(String template, String varStart, String varEnd, Map<String, String> values) {
        String s = template;

        String key;
        for (Iterator var5 = values.keySet().iterator(); var5.hasNext(); s = s.replace(varStart + key + varEnd, (CharSequence) (values.get(key) == null ? "" : (CharSequence) values.get(key)))) {
            key = (String) var5.next();
        }

        return s;
    }

    public static File getFileFromBytes(byte[] b, String outputFile) throws Exception {
        BufferedOutputStream stream = null;
        FileOutputStream fstream = null;
        File file = null;

        try {
            file = new File(outputFile);
            fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception var9) {
            throw var9;
        } finally {
            IOUtils.closeQuietly(stream);
            IOUtils.closeQuietly(fstream);
        }

        return file;
    }

    public static String replaceOrCutUserName(int len, String userName) {
        String str = realplayMobleAndEmail(userName);
        if (!str.equals(userName)) {
            return str;
        } else if (userNamePt.matcher(userName).find() && validMobileNumber(userName.substring(2, userName.length()))) {
            return userName.substring(0, userName.length() - 4) + "****";
        } else {
            return len == -1 ? str : cutString2(len, str);
        }
    }

    public static String hidenUserName(String userName) {
        return isBlank(userName) ? userName : userName.substring(0, 1) + "**";
    }

    public static String realplayMobleAndEmail(String str) {
        try {
            String s = str;
            Matcher m2 = emailPattern.matcher(str);
            Matcher m = phonePattern.matcher(str);
            if (str.length() == 11 && m.find()) {
                String st = str.substring(0, 7);
                s = st + "****";
            } else if (m2.find()) {
                int i = str.indexOf("@");
                String st = str.substring(0, i + 1);
                s = st + "****";
            }

            return s;
        } catch (Exception var8) {
            return str;
        }
    }

    public static String cutString2(int len, String source) {
        return source.length() >= len ? source.substring(0, len) + "… " : source;
    }

    public static String cutString(int len, String source) {
        return source.length() >= len ? source.substring(0, len) + "… " : source;
    }

    public static String coverNullStrValue(String value) {
        return !"null".equals(value) && value != null ? value : "";
    }

    public static String trimNullValue(String object) {
        return object != null && !"null".equals(object) ? object : "";
    }

    public static String arrToStr(String[] arr) {
        String str = null;
        if (arr != null) {
            str = org.springframework.util.StringUtils.quote(org.springframework.util.StringUtils.arrayToDelimitedString(arr, "','"));
        }

        return str;
    }

    public static String filterOutHTMLTags(String inputString) {
        String htmlStr = inputString;
        String textStr = "";

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>";
            Pattern p_script = Pattern.compile(regEx_script, 2);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");
            Pattern p_style = Pattern.compile(regEx_style, 2);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");
            Pattern p_html = Pattern.compile(regEx_html, 2);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");
            textStr = htmlStr;
        } catch (Exception var12) {
            System.err.println("Html2Text: " + var12.getMessage());
        }

        return textStr;
    }

    public static boolean isIdCard(String idNumber) {
        String pattern;
        Pattern p;
        Matcher m;
        if (idNumber.length() == 18) {
            pattern = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))";
            p = Pattern.compile(pattern);
            m = p.matcher(idNumber);
            return m.matches();
        } else {
            pattern = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((([02468][048])|([13579][26]))0229))|([0-9][0-9])((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))(\\d{3})";
            p = Pattern.compile(pattern);
            m = p.matcher(idNumber);
            return m.matches();
        }
    }

    public static boolean isPinyin(String keyword) {
        Matcher m = pinyinPattern.matcher(keyword);
        return m.matches();
    }

    public static boolean isNumber(String str) {
        return isBlank(str) ? false : str.matches("^\\d+");
    }

    public static String outputJSContent(String str) {
        return isNotEmpty(str) ? str.replaceAll("\"", "\\\\\"") : "";
    }

    public static String outputCoremetricsParam(String str) {
        if (isNotEmpty(str)) {
            str = str.replaceAll("\\(", "");
            str = str.replaceAll("\\)", "");
            return str;
        } else {
            return "";
        }
    }

    public static boolean isSame(String s1, String s2) {
        if (s1 != null) {
            return s1.equals(s2);
        } else {
            return s2 != null ? s2.equals(s1) : true;
        }
    }

    public static boolean isNumeric(String content) {
        try {
            Double.parseDouble(content);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static boolean isDate(String strDate) {
        if (isBlank(strDate)) {
            return false;
        } else {
            Matcher m = datePattern.matcher(strDate);
            return m.matches();
        }
    }

    public static String getContent(Map<String, String> params) {
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        boolean first = true;

        for (int i = 0; i < keys.size(); ++i) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            if (value != null && value.trim().length() != 0) {
                if (first) {
                    prestr = prestr + key + "=" + value;
                    first = false;
                } else {
                    prestr = prestr + "&" + key + "=" + value;
                }
            }
        }

        return prestr;
    }

    public static String convertMapToString(Map<String, String> map) {
        StringBuffer prestr = new StringBuffer();
        Iterator var2 = map.entrySet().iterator();

        while (var2.hasNext()) {
            Entry<String, String> entry = (Entry) var2.next();
            prestr.append("&" + (String) entry.getKey() + "=" + (entry.getValue() == null ? "" : (String) entry.getValue()));
        }

        if (prestr.length() > 0) {
            return prestr.toString().substring(1);
        } else {
            return prestr.toString();
        }
    }

    public static String getRootPath(URL url) {
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf(33);
        return -1 == pos ? fileUrl : fileUrl.substring(5, pos);
    }

    public static String dotToSplash(String name, String... ignoreSuffix) {
        if (null == ignoreSuffix) {
            return name.replaceAll("\\.", "/");
        } else {
            String suffix0 = null;
            String[] var3 = ignoreSuffix;
            int var4 = ignoreSuffix.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String suffix = var3[var5];
                if (name.endsWith("." + suffix)) {
                    suffix0 = suffix;
                    break;
                }
            }

            if (null == suffix0) {
                return name.replaceAll("\\.", "/");
            } else if (!name.endsWith("." + suffix0)) {
                return name.replaceAll("\\.", "/");
            } else {
                name = name.replace("." + suffix0, "##" + suffix0);
                name = name.replaceAll("\\.", "/");
                name = name.replaceAll("##" + suffix0, "." + suffix0);
                return name;
            }
        }
    }

    public static String splashToDot(String name, String... ignoreSuffix) {
        if (null == ignoreSuffix) {
            return name.replaceAll("\\/", "\\.");
        } else {
            String suffix0 = null;
            String[] var3 = ignoreSuffix;
            int var4 = ignoreSuffix.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String suffix = var3[var5];
                if (name.endsWith("." + suffix)) {
                    suffix0 = suffix;
                    break;
                }
            }

            if (null == suffix0) {
                return name.replaceAll("\\/", "\\.");
            } else if (!name.endsWith("." + suffix0)) {
                return name.replaceAll("\\/", "\\.");
            } else {
                name = name.replace("." + suffix0, "##" + suffix0);
                name = name.replaceAll("\\/", "\\.");
                name = name.replaceAll("##" + suffix0, "." + suffix0);
                return name;
            }
        }
    }

    public static String trimExtension(String name) {
        int pos = name.lastIndexOf(".");
        return -1 != pos ? name.substring(0, pos) : name;
    }

    public static String trimURI(String uri) {
        String trimmed = uri.substring(1);
        int splashIndex = trimmed.indexOf(47);
        return trimmed.substring(splashIndex);
    }

    public static String lpud(String src, CharSequence target, int length) {
        if (isEmptyString(src)) {
            return src;
        } else {
            StringBuilder buf = new StringBuilder(src);

            while (buf.length() < length) {
                buf.insert(0, target);
            }

            return buf.toString();
        }
    }


    public static int chineseNumberToInt(String chineseNumber) {
        int result = 0;
        int temp = 1;
        int count = 0;
        char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
        char[] chArr = new char[]{'十', '百', '千', '万', '亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {
                if (c == cnArr[j]) {
                    if (0 != count) {
                        result += temp;
                        temp = 1;
                        count = 0;
                    }

                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if (b) {
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                        }


                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {
                result += temp;
            }
        }
        return result;
    }

    public static boolean isUuid(String str) {
        boolean result = false;

        try {
            if (isNotEmptyString(str)) {
                UUID.fromString(str);
                result = true;
            }
        } catch (Exception var3) {
            result = false;
        }

        return result;
    }

    public static String emptyIf(String value, String defaultValue) {
        return isInvalid(value) ? defaultValue : value;
    }

    public static String makeAllWordFirstLetterUpperCase(String sqlName) {
        String[] strs = sqlName.toLowerCase().split("_");
        String result = "";
        String preStr = "";

        for (int i = 0; i < strs.length; ++i) {
            if (preStr.length() == 1) {
                result = result + strs[i];
            } else {
                result = result + capitalize(strs[i]);
            }

            preStr = strs[i];
        }

        return result;
    }

    public static String getString(String str0, String str1) {
        return isInvalid(str0) ? str1 : str0;
    }

    public static String changFirstWord(String str, int changType) {
        if (isInvalid(str)) {
            return "";
        } else {
            String first = str.substring(0, 1);
            String second = str.substring(1);
            switch (changType) {
                case 1:
                    first = first.toLowerCase();
                    break;
                case 2:
                    first = first.toUpperCase();
            }

            String result = first + second;
            return result;
        }
    }

    public static boolean isInvalid(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static String replace(String inString, String oldPattern, String newPattern) {
        if (inString == null) {
            return null;
        } else if (oldPattern != null && newPattern != null) {
            StringBuffer sbuf = new StringBuffer();
            int pos = 0;
            int index = inString.indexOf(oldPattern);

            for (int patLen = oldPattern.length(); index >= 0; index = inString.indexOf(oldPattern, pos)) {
                sbuf.append(inString.substring(pos, index));
                sbuf.append(newPattern);
                pos = index + patLen;
            }

            sbuf.append(inString.substring(pos));
            return sbuf.toString();
        } else {
            return inString;
        }
    }

    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str != null && str.length() != 0) {
            StringBuffer buf = new StringBuffer(str.length());
            if (capitalize) {
                buf.append(Character.toUpperCase(str.charAt(0)));
            } else {
                buf.append(Character.toLowerCase(str.charAt(0)));
            }

            buf.append(str.substring(1));
            return buf.toString();
        } else {
            return str;
        }
    }

    public static String randomNumeric(int count) {
        return random(count, false, true);
    }

    public static String random(int count, boolean letters, boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, (char[]) null, RANDOM);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random) {
        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        } else {
            if (start == 0 && end == 0) {
                end = 123;
                start = 32;
                if (!letters && !numbers) {
                    start = 0;
                    end = 2147483647;
                }
            }

            char[] buffer = new char[count];
            int gap = end - start;

            while (true) {
                while (true) {
                    while (count-- != 0) {
                        char ch;
                        if (chars == null) {
                            ch = (char) (random.nextInt(gap) + start);
                        } else {
                            ch = chars[random.nextInt(gap) + start];
                        }

                        if (letters && Character.isLetter(ch) || numbers && Character.isDigit(ch) || !letters && !numbers) {
                            if (ch >= '\udc00' && ch <= '\udfff') {
                                if (count == 0) {
                                    ++count;
                                } else {
                                    buffer[count] = ch;
                                    --count;
                                    buffer[count] = (char) ('\ud800' + random.nextInt(128));
                                }
                            } else if (ch >= '\ud800' && ch <= '\udb7f') {
                                if (count == 0) {
                                    ++count;
                                } else {
                                    buffer[count] = (char) ('\udc00' + random.nextInt(128));
                                    --count;
                                    buffer[count] = ch;
                                }
                            } else if (ch >= '\udb80' && ch <= '\udbff') {
                                ++count;
                            } else {
                                buffer[count] = ch;
                            }
                        } else {
                            ++count;
                        }
                    }

                    return new String(buffer);
                }
            }
        }
    }

    public static String toUnderscoreName(String name) {
        if (name == null) {
            return null;
        } else {
            String filteredName = name;
            if (name.indexOf("_") >= 0 && name.equals(name.toUpperCase())) {
                filteredName = name.toLowerCase();
            }

            if (filteredName.indexOf("_") == -1 && filteredName.equals(filteredName.toUpperCase())) {
                filteredName = filteredName.toLowerCase();
            }

            StringBuffer result = new StringBuffer();
            if (filteredName != null && filteredName.length() > 0) {
                result.append(filteredName.substring(0, 1).toLowerCase());

                for (int i = 1; i < filteredName.length(); ++i) {
                    String preChart = filteredName.substring(i - 1, i);
                    String c = filteredName.substring(i, i + 1);
                    if (c.equals("_")) {
                        result.append("_");
                    } else if (preChart.equals("_")) {
                        result.append(c.toLowerCase());
                    } else if (c.matches("\\d")) {
                        result.append(c);
                    } else if (c.equals(c.toUpperCase())) {
                        result.append("_");
                        result.append(c.toLowerCase());
                    } else {
                        result.append(c);
                    }
                }
            }

            return result.toString();
        }
    }

    public static String getLastSection(String str, String split) {
        String str2 = str.substring(str.lastIndexOf(split) + 1, str.length());
        return str2;
    }

    public static String getBySplitIndex(String str, String split, int index) {
        int index0 = str.indexOf(split);
        String str2 = str;

        for (int count = 0; index0 > 0 && count < index; index0 = str2.indexOf(split)) {
            ++count;
            str2 = str.substring(index0 + 1);
        }

        return str2;
    }

    public static boolean ipCheck(String text) {
        if (isNotEmpty(text)) {
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            return text.matches(regex);
        } else {
            return false;
        }
    }

    public static String leftPad(Integer value, int size, String padStr) {
        return leftPad((String) String.valueOf(value), size, padStr);
    }

    public static String leftPad(Long value, int size, String padStr) {
        return leftPad((String) String.valueOf(value), size, padStr);
    }

    public static String leftPad(Byte value, int size, String padStr) {
        return leftPad((String) String.valueOf(value), size, padStr);
    }

    public static String leftPad0(Integer value, int size) {
        return leftPad((String) String.valueOf(value), size, "0");
    }

    public static String leftPad0(Long value, int size) {
        return leftPad((String) String.valueOf(value), size, "0");
    }

    public static String leftPad0(Byte value, int size) {
        return leftPad((String) String.valueOf(value), size, "0");
    }
}
