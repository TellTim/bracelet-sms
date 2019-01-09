package com.bracelet.sms.bracelet.model;

import android.support.annotation.NonNull;

import com.bracelet.sms.app.constant.StatusConst;
import com.bracelet.sms.app.manager.AppExecutors;
import com.bracelet.sms.base.IBaseCallback;

import org.litepal.FluentQuery;
import org.litepal.Operator;

import java.util.List;

/**
 * @program: bracelet-sms
 * @packageName: com.bracelet.sms.bracelet.model
 * @fileName: BraceletModel
 * @author: Tell.Tim
 * @date: 2019/1/8 18:40
 * @description:
 */
public class BraceletModel implements IBraceletModel {

    @Override
    public void insertBracelet(@NonNull BraceletBean braceletBean) {
        if (braceletBean!=null){
            AppExecutors.getDiskIOPool().execute(
                    () -> braceletBean.saveOrUpdate("telephone = ?",braceletBean.getTelephone()));
        }
    }

    @Override
    public void queryAllBracelets(@NonNull IBaseCallback<List<BraceletBean>> baseCallback) {
        Runnable runnable = () -> {
            final List<BraceletBean> modelBeanList = Operator.findAll(BraceletBean.class);
            AppExecutors.getMainThread().execute(() -> {
                if (modelBeanList == null) {
                    baseCallback.onFailure(StatusConst.EMPTY_RESULT);
                } else {
                    baseCallback.onSuccess(modelBeanList);
                }
            });
        };
        AppExecutors.getDiskIOPool().execute(runnable);
    }

    @Override
    public void deleteBraceletById(@NonNull int id) {

    }

    @Override
    public void deleteAllBracelets(@NonNull IBaseCallback baseCallback) {

    }

    @Override
    public void updateBracelet(@NonNull BraceletBean braceletBean) {

    }
}
