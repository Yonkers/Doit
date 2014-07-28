package com.yong.doit.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    /**
     * 获取天毫秒数
     *
     * @param days
     * @return
     */
    public static long getDaysMillis(int days) {
        return days * 24 * 60 * 60 * 1000;
    }

    /**
     * 将日期的时间部分清除
     *
     * @param date
     * @return
     */
    public static Date skipTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date d = cal.getTime();
        cal.clear();
        return d;
    }

    public static List<Date> getMemoryPoints(Date date) {

        return null;
    }
}
