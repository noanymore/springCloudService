package com.sqc95111.demologin.model.VO;

import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/1/18 19:11
 * @Version 1.0
 */

public class test {

    public static Date truncateTo10Min(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar gval = Calendar.getInstance();
        gval.setTime(date);
        modify(gval);
        return gval.getTime();
    }

    private static void modify(Calendar val) {
        if (val.get(Calendar.YEAR) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        }

        final Date date = val.getTime();
        long time = date.getTime();

        // truncate milliseconds
        final int millisecs = val.get(Calendar.MILLISECOND);
        time = time - millisecs;

        // truncate seconds
        final int seconds = val.get(Calendar.SECOND);
        time = time - (seconds * 1000L);

        // truncate minutes
        final int minutes = val.get(Calendar.MINUTE);
        int minusMin = minutes % 10;
        time = time - (minusMin * 60000L);

        // reset time
        if (date.getTime() != time) {
            date.setTime(time);
            val.setTime(date);
        }
    }


    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(truncateTo10Min(date));
        Date date2 = DateUtils.addHours(truncateTo10Min(new Date()),1);
        System.out.println(date2);
    }
}
