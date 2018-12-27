package com.bracelet.sms.db;

import android.graphics.Path;
import android.text.TextUtils;

import com.bracelet.sms.db.model.Bracelet;

import org.litepal.Operator;

import java.util.List;

/**
 * Created by Tim.WJ on 2018/12/23.
 */
public class DBManager {

    private static final String TAG = "DBManager";
    private static DBManager mInstance;

    private DBManager() {
    }

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    public synchronized void saveOrUpdateFriend(Bracelet bracelet) {
        if (bracelet != null) {
            String telephone = bracelet.getTelephone();
            if (TextUtils.isEmpty(telephone)) {
                return;
            }
            bracelet.saveOrUpdate("telephone = ?", bracelet.getTelephone());
        }
    }

    public synchronized List<Bracelet> getBracelets() {
        return Operator.findAll(Bracelet.class);
    }
}
