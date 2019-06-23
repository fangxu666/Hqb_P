package com.bestnet.hf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 说明：常用String操作工具类
 *
 * 作者：hzg
 *
 * 时间：2019-06-12
 *
 * */

public class StringUtil {
    public static SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat yyyyMMddHHmmssFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat yyMMFormat = new SimpleDateFormat("yyMM");
    public static SimpleDateFormat yyyyMMddFormatII = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static SimpleDateFormat yyyyMMddFormatI = new SimpleDateFormat(
            "yyyy/MM/dd");
    public static SimpleDateFormat defaultFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat yyyyMMddHHmmssFormatI = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");
    public static SimpleDateFormat PERSONAL_FORMAT = new SimpleDateFormat("MM月dd日HH时mm分");

    private static String CUR_YEAR;// 当前所属年
    private static String CUR_MONTH;// 当前所属年月
    private static String CUR_DATE;// 当前所属年月日

    public static Date CUR_DATE_D = new Date();//当前日期
    static{
        Calendar c = Calendar.getInstance();
        c.setTime(CUR_DATE_D);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 判断字符串是否为空或NULL
     *
     * @param src
     *            源字符串
     * @return true：为空
     */
    public static boolean isEmpty(String src) {
        if (src == null || src.length() == 0 || src.equals("null")) {
            return true;
        }
        return false;
    }

    /**
     * 将日期对象按传入的格式转换成字符串
     *
     * @param date
     *            日期对象
     * @return 日期字符串
     */
    public static String dateToStr(Object date) {
        if (date == null){
            return "";}
        if (date instanceof String){
            return (String) date;}
        return defaultFormat.format(date);
    }

    /**
     * 将日期对象按传入的格式转换成字符串
     *
     * @param date
     *            日期对象
     * @return 日期字符串
     */
    public static String dateToStr(Date date) {
        return defaultFormat.format(date);
    }

    /**
     * 将日期对象按传入的格式转换成字符串
     *
     * @param date
     *            日期对象
     * @param format
     *            格式
     * @return 日期字符串
     */
    public static String dateToStr(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将日期字符串按传入的格式转换成日期对象
     *
     * @param src
     *            日期字符串
     * @param format
     *            格式
     * @return 日期
     * @throws ParseException
     */
    public static Date strToDate(String src, String format)
            throws ParseException {
        return new SimpleDateFormat(format).parse(src);
    }


    public static void main(String[] args) {
        System.out.println(yyyyMMddHHmmssFormat.format(new Date()));
    }

}
