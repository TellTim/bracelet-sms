package com.bracelet.sms.conversation.model;

import android.support.annotation.NonNull;

import com.bracelet.protocol.model.Operation;
import com.bracelet.sms.base.IBaseCallback;

import java.util.List;

/**
 * @author :Tim.WJ
 * Created on 2019/1/13.
 */
public interface IOperationModel {

    void reQueryAllOperation(@NonNull final IBaseCallback<List<Operation>> baseCallback);
}
