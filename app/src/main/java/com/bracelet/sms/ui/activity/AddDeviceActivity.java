package com.bracelet.sms.ui.activity;

import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.bracelet.sms.R;
import com.bracelet.sms.ui.base.BaseActivity;
import com.bracelet.sms.ui.presenter.AddDeviceAtPresenter;
import com.bracelet.sms.ui.presenter.view.IAddDeviceAtView;
import com.bracelet.sms.ui.utils.UIUtils;
import com.bracelet.sms.utils.RegularUtils;

import butterknife.BindView;

/**
 * Created by Tim.WJ on 2018/12/23.
 */
public class AddDeviceActivity extends BaseActivity<IAddDeviceAtView, AddDeviceAtPresenter> implements IAddDeviceAtView {

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
        setToolbarTitle(UIUtils.getString(R.string.str_add_device));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mCommitBtn.setOnClickListener(v -> mPresenter.binding());
    }

    @Override
    protected boolean isToolbarCanBack() {
        return super.isToolbarCanBack();
    }

    @Override
    protected AddDeviceAtPresenter createPresenter() {
        return new AddDeviceAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_add_device;
    }

    @Override
    public TextInputEditText getNickname() {
        return mNickName;
    }

    @Override
    public TextInputEditText getMobileNumber() {
        return mTelephone;
    }
}
