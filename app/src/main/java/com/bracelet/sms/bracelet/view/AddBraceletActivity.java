package com.bracelet.sms.bracelet.view;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import butterknife.BindView;
import com.bracelet.sms.R;
import com.bracelet.sms.app.action.AppAction;
import com.bracelet.sms.app.manager.BroadcastManager;
import com.bracelet.sms.base.BaseActivity;
import com.bracelet.sms.bracelet.presenter.AddBraceletPresenter;
import com.bracelet.sms.utils.UIUtils;

/**
 *
 * @author Tim.WJ
 * @date 2018/12/23
 */
public class AddBraceletActivity extends BaseActivity<IAddBraceletView, AddBraceletPresenter> implements
        IAddBraceletView {

    @BindView(R.id.et_nickname)
    TextInputEditText mNickName;
    @BindView(R.id.et_mobile)
    TextInputEditText mTelephone;
    @BindView(R.id.btn_commit)
    Button mCommitBtn;

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle(UIUtils.getString(R.string.str_add_bracelet));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mCommitBtn.setOnClickListener(v -> mPresenter.bindingBracelet(mTelephone.getText().toString().trim(),
                mNickName.getText().toString().trim()));
    }

    @Override
    protected boolean isToolbarCanBack() {
        return super.isToolbarCanBack();
    }

    @Override
    protected AddBraceletPresenter createPresenter() {
        return new AddBraceletPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_add_bracelet;
    }

    @Override
    public void showAddSuccessAndJumpBack() {
        showToast(UIUtils.getString(R.string.binding_success));
        BroadcastManager.getInstance(this).sendBroadcast(AppAction.UPDATE_BRACELET_LIST);
        this.finish();
    }
}




