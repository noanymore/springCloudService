package com.sqc95111.datasynchronize.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
    private static final Log logger = LogFactory.getLog(DateUtil.class);

    // 默认显示日期的格式
    public static final String DATAFORMAT_STR_YYYY_MM_SLASH = "yyyy/MM";

    // 默认显示日期的格式 yyyy/MM/dd
    public static final String DATAFORMAT_STR_YYYY_MM_DD_SLASH = "yyyy/MM/dd";

    // 默认显示日期时间的格式
    public static final String DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS_SLASH = "yyyy/MM/dd HH:mm:ss";

    // 默认显示日期的格式
    public static final String DATAFORMAT_STR_YYYY_MM_BAR = "yyyy-MM";

    // 默认显示日期的格式 yyyy-MM-dd
    public static final String DATAFORMAT_STR_YYYY_MM_DD_BAR = "yyyy-MM-dd";

    // 默认显示日期时间的格式
    public static final String DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS_BAR = "yyyy-MM-dd HH:mm:ss";

    // 默认显示简体中文日期的格式
    public static final String ZHCN_DATAFORMAT_STR_YYYY_MM = "yyyy年MM月";

    // 默认显示简体中文日期的格式
    public static final String ZHCN_DATAFORMAT_STR_YYYY_MM_DD = "yyyy年MM月dd日";

    // 默认显示简体中文日期时间的格式
    public static final String ZHCN_DATATIMEF_STR_YYYY_MM_DD_HHMMSS = "yyyy年MM月dd日 HH:mm:ss";

    // 默认显示简体中文日期时间的格式
    public static final String ZHCN_DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS = "yyyy年MM月dd日HH时mm分ss秒";

    @SuppressWarnings("deprecation")
    public static int getMonthDiff(Date startDate, Date endDate) {
        int result = 0;

        int yeardiff = endDate.getYear() - startDate.getYear();
        int nonthdiff = endDate.getMonth() - startDate.getMonth();
        result = yeardiff * 12 + nonthdiff;
        return result;
    }

    /**
     * 获取本周一0点的日期
     * 
     * @return
     */
    public static Date getThisWeekFirstDayDate() {
        Date result;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 1);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        result = c.getTime();
        return result;
    }

    /**
     * 日期 -> 字符串（格式化日期）
     * 
     * @author bl00252
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        if (date == null)
            return "";
        DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ymdhmsFormat.format(date);
    }

    /**
     * 日期 -> 字符串（格式化日期）
     * 
     * @author bl00252
     * @param date
     * @return
     */
    public static String dateToStr(Date date, String format) {
        if (date == null)
            return "";
        DateFormat ymdhmsFormat = new SimpleDateFormat(format);
        return ymdhmsFormat.format(date);
    }

    /**
     * 日期 -> 字符串（格式化日期） for JXL JXL解析出来的日期时区是GMT，需要转化为本地时区，不然差8个小时
     * 
     * @author bl00252
     * @param date
     * @return
     */
    public static String dateToStrForJxl(Date date, String format) {
        if (date == null)
            return "";
        try {
            date = convertDate4JXL(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat ymdhmsFormat = new SimpleDateFormat(format);
        ymdhmsFormat.setTimeZone(TimeZone.getDefault());
        return ymdhmsFormat.format(date);
    }

    /**
     * JXL中通过DateCell.getDate()获取单元格中的时间为（实际填写日期+8小时），原因是JXL是按照GMT时区来解析XML。
     * 本方法用于获取单元格中实际填写的日期！ 例如单元格中日期为“2009-9-10”，getDate得到的日期便是“Thu Sep 10
     * 08:00:00 CST 2009”；单元格中日期为“2009-9-10 16:00:00”，getDate得到的日期便是“Fri Sep 11
     * 00:00:00 CST 2009”
     * 
     * @param jxlDate
     *            通过DateCell.getDate()获取的时间
     * @return
     * @throws ParseException
     */

    public static Date convertDate4JXL(Date jxlDate) throws ParseException {

        if (jxlDate == null)
            return null;

        TimeZone gmt = TimeZone.getTimeZone("GMT");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        dateFormat.setTimeZone(gmt);

        String str = dateFormat.format(jxlDate);

        TimeZone local = TimeZone.getDefault();

        dateFormat.setTimeZone(local);

        return dateFormat.parse(str);

    }

    /**
     * 字符串 -> 日期
     * 
     * @author bl00252
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String str) {
        try {
            DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return ymdhmsFormat.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串转换为日期
     * 
     * @param str
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String str, String format) {
        try {
            DateFormat ymdhmsFormat = new SimpleDateFormat(format);
            return ymdhmsFormat.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date autoFormatDate(String dateString, String format) {
        Date result = null;
        try {
            if (isDateFormatOk(dateString, format)) {
                result = strToDate(dateString, format);
            }
            else {
                format = getFormateStr(dateString);
                result = strToDate(dateString, format);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断字符日期格式是否正确
     * 
     * @param str
     * @param format
     * @return
     */
    public static boolean isDateFormatOk(String str, String format) {
        try {
            DateFormat ymdhmsFormat = new SimpleDateFormat(format);
            ymdhmsFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 设置date的时间为当天的结束时间, 即23:59:59
     * 
     * @param date
     * @return
     */
    public static Date setTimeToTheEndOfTheDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 设置date的时间为当天的开始时间, 即00:00:00
     * 
     * @param date
     * @return
     */
    public static Date setTimeToTheStartOfTheDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 设置date的时间为当月的开始时间
     * 
     * @param date
     * @return
     */
    public static Date setTimeToTheStartOfTheMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 设置date的时间为当月的结束时间
     * 
     * @param date
     * @return
     */
    public static Date setTimeToTheEndOfTheMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));

        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 根据一个日期字符串，返回日期格式，目前支持4种 如果都不是，则返回null
     * 
     * @param DateString
     * @return
     */
    public static String getFormateStr(String DateString) {
        // 默认显示日期的格式
        String pattern_yyyy_mm_slash = "\\d{4}\\/\\d{1,2}"; // yyyy/MM
        // 默认显示日期的格式
        String pattern_yyyy_mm_dd_slash = "\\d{4}\\/\\d{1,2}\\/\\d{1,2}"; // yyyy/MM/dd
        // 默认显示日期时间的格式
        String pattern_4y_mm_dd_hhmmss_slash = "\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}"; // yyyy/MM/dd
                                                                                                            // HH:mm:ss;

        // 默认显示日期的格式
        String pattern_yyyy_mm_bar = "\\d{4}-\\d{1,2}"; // yyyy-MM
        // 默认显示日期的格式
        String pattern_yyyy_mm_dd_bar = "\\d{4}-\\d{1,2}-\\d{1,2}"; // yyyy-MM-dd
        // 默认显示日期时间的格式
        String pattern_4y_mm_dd_hhmmss_bar = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}"; // yyyy-MM-dd
                                                                                                      // HH:mm:ss;

        // 默认显示简体中文日期的格式
        String zhcn_pattern_4y_mm = "\\d{4}年\\d{1,2}月";
        // 默认显示简体中文日期的格式
        String zhcn_pattern_4y_mm_dd = "\\d{4}年\\d{1,2}月\\d{1,2}日";// yyyy年MM月dd日
        // 默认显示简体中文日期的格式
        String zhcn_pattern_4y_mm_dd_hhmmss = "\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";// yyyy年MM月dd日
        // 默认显示简体中文日期时间的格式
        String zhcn_pattern_4y_mm_dd_hh_mm_ss = "\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}时\\d{1,2}分\\d{1,2}秒";// yyyy年MM月dd日HH时mm分ss秒

        // yyyy/MM
        Pattern p = Pattern.compile(pattern_yyyy_mm_slash);
        Matcher m = p.matcher(DateString);
        boolean b = m.matches();
        if (b)
            return DATAFORMAT_STR_YYYY_MM_SLASH;
        // yyyy/MM/dd
        p = Pattern.compile(pattern_yyyy_mm_dd_slash);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return DATAFORMAT_STR_YYYY_MM_DD_SLASH;
        // yyyy/MM/dd HH:mm:ss
        p = Pattern.compile(pattern_4y_mm_dd_hhmmss_slash);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS_SLASH;

        // yyyy-MM
        p = Pattern.compile(pattern_yyyy_mm_bar);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return DATAFORMAT_STR_YYYY_MM_BAR;
        // yyyy-MM-dd
        p = Pattern.compile(pattern_yyyy_mm_dd_bar);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return DATAFORMAT_STR_YYYY_MM_DD_BAR;
        // yyyy-MM-dd HH:mm:ss
        p = Pattern.compile(pattern_4y_mm_dd_hhmmss_bar);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS_BAR;

        // yyyy年MM月
        p = Pattern.compile(zhcn_pattern_4y_mm);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return ZHCN_DATAFORMAT_STR_YYYY_MM;
        // yyyy年MM月dd日
        p = Pattern.compile(zhcn_pattern_4y_mm_dd);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return ZHCN_DATAFORMAT_STR_YYYY_MM;
        // yyyy年MM月dd日 HH:mm:ss
        p = Pattern.compile(zhcn_pattern_4y_mm_dd_hhmmss);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return ZHCN_DATATIMEF_STR_YYYY_MM_DD_HHMMSS;
        // yyyy年MM月dd日HH时mm分ss秒
        p = Pattern.compile(zhcn_pattern_4y_mm_dd_hh_mm_ss);
        m = p.matcher(DateString);
        b = m.matches();
        if (b)
            return ZHCN_DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS;

        return DATAFORMAT_STR_YYYY_MM_DD_SLASH;
    }

    public static Date fromXMLCalendar(XMLGregorianCalendar xmlCalendar) {
        if (xmlCalendar == null)
            return null;
        Calendar cal = xmlCalendar.toGregorianCalendar();
        return cal.getTime();
    }

    public static java.sql.Date fromXMLCalendarAsSqlDate(XMLGregorianCalendar xmlCalendar) {
        if (xmlCalendar == null)
            return null;
        Calendar cal = xmlCalendar.toGregorianCalendar();
        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        return date;
    }

    public static XMLGregorianCalendar toXMLCalendar(Date date) {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        XMLGregorianCalendar cal = null;
        try {
            cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            cal.setYear(calendar.get(Calendar.YEAR));
            cal.setMonth(calendar.get(Calendar.MONTH) + 1);
            cal.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            cal.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            cal.setMinute(calendar.get(Calendar.MINUTE));
            cal.setSecond(calendar.get(Calendar.SECOND));
            cal.setMillisecond(calendar.get(Calendar.MILLISECOND));
        } catch (DatatypeConfigurationException e) {
            logger.error("error when generate XML Calendar");
            logger.error(e);
        }
        return cal;
    }

    /**
     * 字符串转换成时间
     * 
     * @param formatStr
     * @param dateStr
     * @return
     */
    public static Date getDate(String dateStr, String formatStr) {
        Date date = null;
        if (formatStr != null && dateStr != null) {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            try {
                date = format.parse(dateStr);
            } catch (ParseException e) {
                logger.info("getDate error.");
            }
        }
        return date;
    }

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

    /**
     * 拆分时间段
     * 
     * @param from
     *            开始时间
     * @param to
     *            结束时间
     * @param min
     *            间隔分钟数
     * @return
     */
    public static List<Map<String, Date>> getSplitDate(Date from, Date to, int min) {
        List<Map<String, Date>> list = new ArrayList<Map<String, Date>>();

        Calendar cfrom = Calendar.getInstance();
        cfrom.setTime(from);
        Calendar cTo = Calendar.getInstance();
        cTo.setTime(to);

        Calendar cTmp = (Calendar) cfrom.clone();

        while (cfrom.before(cTo)) {
            cfrom.add(Calendar.MINUTE, min);
            Map<String, Date> map = new HashMap<String, Date>();
            if (cfrom.before(cTo)) {
                map.put("from", cTmp.getTime());
                map.put("to", cfrom.getTime());
            }
            else {
                map.put("from", cTmp.getTime());
                map.put("to", cTo.getTime());
            }
            cTmp.add(Calendar.MINUTE, min);
            System.out.println(map);
            list.add(map);
        }
        return list;

    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static int getDaysBetween(Date from, Date to) {
        int days = (int) ((from.getTime() - to.getTime()) / 1000 / 60 / 60 / 24);
        return days;
    }

    public static int getMinsBetween(Date to, Date from) {
        int mins = (int) ((to.getTime() - from.getTime()) / 1000 / 60);
        return mins;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        String dateStr = "2010-12";
        Date date = autoFormatDate(dateStr, DATAFORMAT_STR_YYYY_MM_BAR);
        System.out.println(dateToStr(date));
        // List<String> dateFormates = new ArrayList<String>();
        //
        // String date10 = "2011/03";
        // String date11 = "2011/03/21";
        // String date12 = "2011/03/21 12:14:30";
        // dateFormates.add(date10);
        // dateFormates.add(date11);
        // dateFormates.add(date12);
        //
        //
        // String date20 = "2011-03";
        // String date21 = "2011-03-21";
        // String date22 = "2011-03-21 12:14:30";
        // dateFormates.add(date20);
        // dateFormates.add(date21);
        // dateFormates.add(date22);
        //
        // String date30 = "2011年03月";
        // String date31 = "2011年03月21日";
        // String date32 = "2011年03月21日 12:14:30";
        // String date33 = "2011年03月21日 12时14分30秒";
        // dateFormates.add(date30);
        // dateFormates.add(date31);
        // dateFormates.add(date32);
        // dateFormates.add(date33);

        // String format="";
        // Date dt;
        // for(String item : dateFormates ){
        // format = getFormateStr(item);
        // System.out.println("Date ["+ item+"] dateFormate:" + format);
        // if( isDateFormatOk(item , format ) ){
        // try{
        // dt = strToDate( item, format);
        // System.out.println(dateToStr(dt));
        // }catch( Exception e){
        // System.out.println(e.getMessage());
        // }
        //
        // }
        // System.out.println("*******************************************");
        // }

        // //2011-12-25
        // Calendar calendar1 = new GregorianCalendar(2011, Calendar.DECEMBER,
        // 25,0,0,0);
        // Date startDate1 = calendar1.getTime();
        // //2012-01-01
        // Calendar calendar2 = new GregorianCalendar(2012, Calendar.JANUARY,
        // 1,0,0,0);
        // Date endDate1 = calendar2.getTime();
        //
        // int diff = getMonthDiff(startDate1,endDate1);
        // System.out.println("month between " + dateToStr(startDate1) + " and "
        // + dateToStr(endDate1) + " is :" + diff);
        //
        // //2011-11-25
        // calendar1 = new GregorianCalendar(2011, Calendar.NOVEMBER, 25,0,0,0);
        // startDate1 = calendar1.getTime();
        // //2011-12-01
        // calendar2 = new GregorianCalendar(2011, Calendar.DECEMBER, 1,0,0,0);
        // endDate1 = calendar2.getTime();
        //
        // diff = getMonthDiff(startDate1,endDate1);
        // System.out.println("month between " + dateToStr(startDate1) + " and "
        // + dateToStr(endDate1) + " is :" + diff);
        //
        // //2012-01-25
        // calendar1 = new GregorianCalendar(2012, Calendar.JANUARY, 25,0,0,0);
        // startDate1 = calendar1.getTime();
        // //2011-12-01
        // calendar2 = new GregorianCalendar(2011, Calendar.DECEMBER, 1,0,0,0);
        // endDate1 = calendar2.getTime();
        //
        // diff = getMonthDiff(startDate1,endDate1);
        // System.out.println("month between " + dateToStr(startDate1) + " and "
        // + dateToStr(endDate1) + " is :" + diff);

        getSplitDate(strToDate("2012-01-01 01:00:00", "yyyy-MM-dd hh:mm:ss"),
                strToDate("2012-01-01 01:59:59", "yyyy-MM-dd hh:mm:ss"), 10);
    }
}
