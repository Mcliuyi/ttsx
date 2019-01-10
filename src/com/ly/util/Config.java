package com.ly.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Config {

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    public static final String REGEX_EMAIL = "^[a-z0-9][\\w\\.\\-]*@[a-z0-9\\-]+(\\.[a-z]{2,5}){1,2}$";




    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }


    /**
     * 获取当前时间
     * @return
     */
    public static String getTime(){
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        return current;
    }


    /**
     * 获取当前年
     * @return
     */
    public static String getYear(){
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String current = sdf.format(time);
        return current;
    }

    /**
     * 获取当前月
     * @return
     */
    public static String getMonth(){
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String current = sdf.format(time);
        return current;
    }



    /**
     * 生产订单idid
     * @return
     */
    public static String getOrderid(){
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddss");
        String current = sdf.format(time);
        return current;
    }






}
