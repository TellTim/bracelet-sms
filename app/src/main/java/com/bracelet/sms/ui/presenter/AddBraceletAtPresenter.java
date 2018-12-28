package com.bracelet.sms.ui.presenter;

import android.text.TextUtils;

import com.bracelet.sms.R;
import com.bracelet.sms.app.action.AppAction;
import com.bracelet.sms.app.manager.BroadcastManager;
import com.bracelet.sms.db.DBManager;
import com.bracelet.sms.db.model.Bracelet;
import com.bracelet.sms.ui.base.BaseActivity;
import com.bracelet.sms.ui.base.BasePresenter;
import com.bracelet.sms.ui.presenter.view.IAddBraceletAtView;
import com.bracelet.sms.ui.utils.UIUtils;

public class AddBraceletAtPresenter extends BasePresenter<IAddBraceletAtView> {
    private static final String TAG = "HomeAtPresenter";

    public AddBraceletAtPresenter(BaseActivity context) {
        super(context);
    }

    public void binding() {

        String bindingNumber = getView().getMobileNumber().getText().toString().trim();
        String bindingNickname = getView().getNickname().getText().toString().trim();

        if (!TextUtils.isEmpty(bindingNumber) && !TextUtils.isEmpty(bindingNickname)) {
            Bracelet bracelet = new Bracelet(bindingNickname, bindingNumber, true);
            DBManager.getInstance().saveOrUpdateFriend(bracelet);
            BroadcastManager.getInstance(mContext).sendBroadcast(AppAction.UPDATE_BRACELET_LIST);
            mContext.finish();
            UIUtils.showToastSafely(UIUtils.getString(R.string.binding_success));
        } else {
            UIUtils.showToastSafely(UIUtils.getString(R.string.parameter_error));
        }
    }
}
