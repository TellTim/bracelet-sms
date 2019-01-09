package com.bracelet.sms.bracelet.model;

import android.support.annotation.NonNull;
import com.bracelet.sms.base.IBaseCallback;

import java.util.List;

/**
 * @program: bracelet-sms
 * @packageName: com.bracelet.sms.bracelet.model
 * @fileName: IBraceletModel
 * @author: Tell.Tim
 * @date: 2019/1/8 18:29
 * @description:
 */
public interface IBraceletModel {
    void insertBracelet(@NonNull BraceletBean braceletBean);

    void queryAllBracelets(@NonNull final IBaseCallback<List<BraceletBean>> baseCallback);

    void deleteBraceletById(@NonNull int id);

    void deleteAllBracelets(@NonNull final IBaseCallback baseCallback);

    void updateBracelet(@NonNull BraceletBean braceletBean);
}
