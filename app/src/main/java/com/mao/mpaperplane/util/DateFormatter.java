package com.mao.mpaperplane.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Created by MaoJunFeng on 2017/3/18.
 */

public class DateFormatter {
    /**
     * 将long类date转换为String类型（知乎）
     * @param date
     * @return
     */
    public static String zhihuDailyDateFormat(long date) {
        Date d = new Date(date + 24 * 60 * 60 * 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(d);
    }

    /**
     * 将long类date转换为String类型（豆瓣）
     * @param date
     * @return
     */
    public static String doubanDateFormat(long date) {
        Date d = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(d);
    }
}
