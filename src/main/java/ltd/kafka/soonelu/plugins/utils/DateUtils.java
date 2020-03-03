package ltd.kafka.soonelu.plugins.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 涉及时间的工具类
 *
 * @author soonelu
 **/
public class DateUtils {

    private final static int SECOND = 1;
    private final static int MINUTE = 2;
    private final static int HOUR = 3;
    private final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final static String DATETIME_CROWD_FORMAT = "YYYYMMddHHmmss";
    private static final String SIMPLE_FORMAT = "yyyy-MM-dd";
    private static final String SIMPLE_SHORT_FORMAT = "yyyyMMdd";
    private static final String YM_FORMAT = "yyyy-MM";
    private static final String MAIL_FORMAT = "yyyy年MM月dd日 HH:mm:ss";
    private static final String HMS_FORMAT = "HH:mm:ss";

    /**
     * 时间转字符串
     *
     * @param date       时间
     * @param dateFormat 给定时间格式
     * @return 字符串形式时间
     */
    private static String date2String(Date date, String dateFormat) {
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(date);
        }
        return null;
    }


    public static String getNowTime() {
        return new SimpleDateFormat(DATETIME_FORMAT)
                .format(new Date(System.currentTimeMillis()));
    }

    /**
     * 时间戳转字符串
     *
     * @param timestamp 时间戳
     * @return 字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String stamp2String(String timestamp) {
        if (StringUtils.isEmpty(timestamp)) {
            return "";
        }
        Long timeMills = Long.valueOf(timestamp);
        return stamp2String(timeMills);
    }

    /**
     * 时间戳转字符串
     *
     * @param timestamp 时间戳
     * @return 字符串 yyyy-MM-dd HH:mm:ss
     */
    private static String stamp2String(Long timestamp) {
        return date2String(new Date(timestamp), DATETIME_FORMAT);
    }

    /**
     * 字符串转时间
     *
     * @param time 2020-01-01 10:10:10
     * @return date
     */
    public static Date string2Date(String time) {
        try {
            return new SimpleDateFormat(DATETIME_FORMAT).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
