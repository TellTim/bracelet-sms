package com.bracelet.sms.utils;

import android.text.TextUtils;
import android.util.Log;

import com.bracelet.sms.app.constant.AppConst;

/**
 * UI层日志工具
 */
public class ULog {
    /**
     * 日志输出级别 LEVEL_OFF
     */
    public static final int LEVEL_OFF = 0;
    /**
     * 日志输出级别All
     */
    public static final int LEVEL_ALL = 7;
    /**
     * 日志输出级别Verbose
     */
    public static final int LEVEL_VERBOSE = 1;
    /**
     * 日志输出级别Debug
     */
    public static final int LEVEL_DEBUG = 2;
    /**
     * 日志输出级别I
     */
    public static final int LEVEL_INFO = 3;
    /**
     * 日志输出级别Warn
     */
    public static final int LEVEL_WARN = 4;
    /**
     * 日志输出级别Error
     */
    public static final int LEVEL_ERROR = 5;
    /**
     * 日志输出级别S,自定义定义的一个级别
     */
    public static final int LEVEL_SYSTEM = 6;
    /**
     * 写文件的锁对象
     */
    private static final Object mLogLock = new Object();
    /**
     * 日志输出时的TAG
     */
    private static String mTag = AppConst.TAG + "-U";
    /**
     * 用于记时的变量
     */
    private static long mTimestamp = 0;
    /**
     * 是否允许输出log
     */
    private static int mDebuggable = AppConst.DEBUGLEVEL;

    /**
     * 以级别为 v 的形式输出LOG
     *
     * @param msg String Log信息
     */
    public static void v(String msg) {
        if (mDebuggable >= LEVEL_VERBOSE) {
            Log.v(mTag, msg);
        }
    }

    /**
     * 以级别为 v 的形式输出LOG
     *
     * @param tag String Log标签
     * @param msg String Log信息
     */
    public static void v(String tag, String msg) {
        if (mDebuggable >= LEVEL_VERBOSE) {
            Log.v(mTag + "-" + tag, msg);
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     *
     * @param msg String Log信息
     */
    public static void i(String msg) {
        if (mDebuggable >= LEVEL_INFO) {
            Log.i(mTag, msg);
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     *
     * @param tag String Log标签
     * @param msg String Log信息
     */
    public static void i(String tag, String msg) {
        if (mDebuggable >= LEVEL_INFO) {
            Log.i(mTag + "-" + tag, msg);
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     *
     * @param msg String Log信息
     */
    public static void d(String msg) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Log.d(mTag, msg);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG,一般用来输出可忽略的警告信息
     *
     * @param msg the msg
     */
    public static void w(String msg) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(mTag, msg);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG,一般用来输出需要关注的警告信息
     *
     * @param tag String Log标签
     * @param msg String Log信息
     */
    public static void w(String tag, String msg) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(mTag + "-" + tag, msg);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG信息和Throwable，一般用来输出需要关注的警告信息
     *
     * @param msg String Log简介
     * @param tr  Throwable 异常信息
     */
    public static void w(String msg, Throwable tr) {
        if (mDebuggable >= LEVEL_ERROR && null != msg) {
            Log.w(mTag, msg, tr);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG，一般用来输出需要关注的异常信息
     *
     * @param tag String Log标签
     * @param msg String Log信息
     */
    public static void e(String tag, String msg) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(mTag + "-" + tag, msg);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable，一般用来<b><font size="3" color="#ff8888">输出需要解决的异常信息</font></b>
     *
     * @param tag String Log标签
     * @param msg String Log信息
     * @param tr  Throwable 异常信息
     */
    public static void e(String tag, String msg, Throwable tr) {
        if (mDebuggable >= LEVEL_ERROR && null != msg) {
            Log.e(mTag + "-" + tag, msg, tr);
        }
    }

    /**
     * 以级别为 s 的形式输出LOG,一般用于格式化输出
     *
     * @param tag String Log标签
     * @param msg String <b><font size="4" color="#ff8888">需要String.format格式化</font></b>
     */
    public static void sf(String tag, String msg) {
        if (mDebuggable >= LEVEL_ERROR) {
            System.out.println(mTag + "-" + tag + " #----------" + msg + "----------");
        }
    }

    /**
     * 以级别为 d 的形式输出msg信息,附带时间戳，用于输出一个时间段起始点
     *
     * @param tag String Log标签
     * @param msg 需要输出的msg
     */
    public static void msgStartTime(String tag, String msg) {
        mTimestamp = System.currentTimeMillis();
        if (!TextUtils.isEmpty(msg)) {
            d(tag, "[Started：" + mTimestamp + "]" + msg);
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     *
     * @param tag String Log标签
     * @param msg String Log信息
     */
    public static void d(String tag, String msg) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Log.d(mTag + "-" + tag, msg);
        }
    }

    /**
     * 以级别为 d 的形式输出msg信息,附带时间戳，用于输出一个时间段结束点
     *
     * @param tag String Log标签
     * @param msg 需要输出的msg
     */
    public static void elapsed(String tag, String msg) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - mTimestamp;
        mTimestamp = currentTime;
        d(tag, "[Elapsed：" + elapsedTime + "]" + msg);
    }
}
