package com.bracelet.sms.ui.fragment;

import com.bracelet.sms.R;
import com.bracelet.sms.home.HomeActivity;
import com.bracelet.sms.base.BaseFragment;
import com.bracelet.sms.ui.presenter.SettingFgPresenter;
import com.bracelet.sms.ui.presenter.view.ISettingFgView;

public class SettingFragment extends BaseFragment<ISettingFgView, SettingFgPresenter> implements ISettingFgView {
    @Override
    protected SettingFgPresenter createPresenter() {
        return new SettingFgPresenter((HomeActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_setting;
    }
}
