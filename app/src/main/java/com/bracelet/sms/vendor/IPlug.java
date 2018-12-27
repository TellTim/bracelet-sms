package com.bracelet.sms.vendor;

import android.content.Context;

public interface IPlug {
    void plugin(Context context);

    void plugout();
}
