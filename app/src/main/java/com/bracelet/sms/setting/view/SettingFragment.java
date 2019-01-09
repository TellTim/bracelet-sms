package com.bracelet.sms.setting.view;

import com.bracelet.sms.R;
import com.bracelet.sms.base.BaseFragment;
import com.bracelet.sms.setting.presenter.SettingFgPresenter;

public class SettingFragment extends BaseFragment<ISettingFgView, SettingFgPresenter> implements ISettingFgView {
    @Override
    protected SettingFgPresenter createPresenter() {
        return new SettingFgPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_setting;
    }
}
