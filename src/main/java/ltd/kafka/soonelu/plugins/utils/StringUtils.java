package ltd.kafka.soonelu.plugins.utils;

/**
 * 添加字符串工具类，为了兼容JB的各种产品，尽量不要用第三方工具包
 *
 * @author soonelu
 */
public class StringUtils {
    /**
     * 判断是空字符串
     *
     * @param cs 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断是非空字符串
     *
     * @param cs 字符串
     * @return 是否非空
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 首字母大写方法
     *
     * @param str 字符串
     * @return 首字母大写结果
     */
    public static String capitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            return str;
        }

        final int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint;
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint;
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    /**
     * 首字母小写方法
     *
     * @param str 字符串
     * @return 首字母小写结果
     */
    public static String uncapitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toLowerCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            return str;
        }

        final int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint;
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint;
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    /**
     * 根据传入的字串,匹配开始到此子串的字符串
     *
     * @param source hello world i'm k k
     * @param match  world
     * @return hello world
     */
    public static String strBf(String source, String match) {
        if (StringUtils.isEmpty(source)) {
            return source;
        }
        if (StringUtils.isEmpty(match)) {
            return source;
        }
        int endIndex = source.indexOf(match);
        return source.substring(0, endIndex + match.length());
    }

    /**
     * 根据传入的字串,匹配此子串后的字符串
     *
     * @param source hello1 world i'm k k
     * @param match  hello
     * @return 1 world i'm k k
     */
    public static String strAfEx(String source, String match) {
        if (StringUtils.isEmpty(source)) {
            return source;
        }
        if (StringUtils.isEmpty(match)) {
            return source;
        }
        int index = source.indexOf(match);
        if (index <= 0) {
            return source;
        }
        return source.substring(index + match.length());
    }


    /**
     * 根据传入的字串,匹配开始到此子串的字符串
     * j
     *
     * @param source ojo
     * @param match  o
     */
    public static String trimStr(String source, String match) {
        if (StringUtils.isEmpty(source) || StringUtils.isEmpty(match) || match.length() > source.length()) {
            return source;
        }
        if (source.startsWith(match)) {
            source = source.substring(match.length());
            source = trimStr(source, match);
        }
        if (source.endsWith(match)) {
            int gap = source.length() - match.length() - 1;
            if (gap <= 0) {
                return source;
            }
            source = source.substring(0, gap);
            source = trimStr(source, match);
        }
        return source;
    }

    public static String captureName(String text) {

        if (text.length() > 0) {
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
        }
        return text;
    }

    public static String getPackage(String generateClassName) {
        int index = generateClassName.lastIndexOf(".");
        if (index > 0) {
            return generateClassName.substring(0, index);
        }
        return null;
    }

    public static void main(String[] args) {
        String s1 = "hello1 world i'm k khello";
        String s2 = "hello";
        s1 = strAfEx(s1, s2);
        System.out.println(s1);
    }
}
