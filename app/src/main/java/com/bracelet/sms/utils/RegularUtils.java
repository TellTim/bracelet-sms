package com.bracelet.sms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Package : com.hunter.im.utils
 * @Auther : tellt
 * @Date : 2018/11/12 15:56
 * @Description: 正则工具类
 */

public class RegularUtils {

    public static boolean isMobile(String phoneNumber) {
        String MOBLIE_PHONE_PATTERN = "^((13[0-9])|(15[0-9])|(18[0-9])|(14[7])|(17[0|6|7|8]))\\d{8}$";
        Pattern p = Pattern.compile(MOBLIE_PHONE_PATTERN);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean validUserName(final String userName) {
        //只能有汉字、数字、字母、下划线不能以下划线开头和结尾
        String regex = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        return userName.matches(regex);
    }

    public static boolean validPassword(final String password) {
        //最短6位，最长16位 {6,16}
        //必须包含1个数字
        //必须包含2个小写字母
        //必须包含2个大写字母
        //必须包含1个特殊字符
        //String regex = "/^.*(?=.{6,16})(?=.*\\d)(?=.*[A-Z]{2,})(?=.*[a-z]{2,})(?=.*[!@#$%^&*?\\(\\)]).*$/";
        String regex = "[0-9a-zA-Z]{6,11}";
        return password.matches(regex);
    }
}
