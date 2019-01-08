package com.bracelet.sms.bracelet.presenter;

import android.text.TextUtils;

import com.bracelet.sms.bracelet.model.BraceletBean;
import com.bracelet.sms.bracelet.model.BraceletModel;
import com.bracelet.sms.bracelet.model.IBraceletModel;
import com.bracelet.sms.base.BasePresenter;
import com.bracelet.sms.bracelet.view.IAddBraceletView;

public class AddBraceletPresenter extends BasePresenter<IAddBraceletView> implements IAddBraceletPresenter{
    private static final String TAG = "HomeAtPresenter";

    private IBraceletModel braceletModel;

    public AddBraceletPresenter() {
        this.braceletModel = new BraceletModel();
    }

    @Override
    public void bindingBracelet(String telephone, String nickname) {
        if(!TextUtils.isEmpty(telephone)&&!TextUtils.isEmpty(nickname)) {
            BraceletBean braceletBean = new BraceletBean(telephone, nickname);
            braceletModel.insertBracelet(braceletBean);
            getView().showAddSuccessAndJumpBack();
        }
    }

    /*public void binding() {

        String bindingNumber = getView().getMobileNumber().getText().toString().trim();
        String bindingNickname = getView().getNickname().getText().toString().trim();

        if (!TextUtils.isEmpty(bindingNumber) && !TextUtils.isEmpty(bindingNickname)) {
            Bracelet bracelet = new Bracelet(bindingNickname, bindingNumber, true);
            DBManager.getInstance().saveOrUpdateFriend(bracelet);
            // TODO move to View
            *//*BroadcastManager.getInstance(mContext).sendBroadcast(AppAction.UPDATE_BRACELET_LIST);
            mContext.finish();*//*
            UIUtils.showToastSafely(UIUtils.getString(R.string.binding_success));
        } else {
            UIUtils.showToastSafely(UIUtils.getString(R.string.parameter_error));
        }
    }*/
}
