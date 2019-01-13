package com.bracelet.sms.app.constant;

import com.bracelet.sms.utils.ULog;

public class AppConst {
    public static final String TAG = "IBRACELET";

    public static final int DEBUGLEVEL = ULog.LEVEL_ALL;//日志输出级别

    public static final class Operation {
        public static final String INDEX = "index";
        public static final String PARENT_INDEX = "parentIndex";
        public static final String RESPONSE_TIMESTAMP = "responseTimestamp";
        public static final String CONTENT = "content";
        public static final String CLASS_NAME = "operation";
    }
}
