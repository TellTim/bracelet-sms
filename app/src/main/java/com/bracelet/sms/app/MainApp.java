package com.bracelet.sms.app;

import com.bracelet.sms.base.BaseApp;
import com.bracelet.sms.utils.SystemUtils;
import com.bracelet.sms.vendor.litePal.LitePalClient;

public class MainApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        //防止内存泄漏
        if (getApplicationInfo().packageName.equals(
                SystemUtils.getCurProcessName(getApplicationContext()))) {

            LitePalClient.getInstance().plugin(this);

        }
    }
}
