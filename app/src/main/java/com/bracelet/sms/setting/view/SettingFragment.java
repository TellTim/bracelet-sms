package com.bracelet.sms.setting.view;

import com.bracelet.sms.R;
import com.bracelet.sms.base.BaseFragment;
import com.bracelet.sms.setting.presenter.SettingFragmentPresenter;

public class SettingFragment extends BaseFragment<ISettingFragmentView, SettingFragmentPresenter> implements ISettingFragmentView {
    @Override
    protected SettingFragmentPresenter createPresenter() {
        return new SettingFragmentPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_setting;
    }
}
