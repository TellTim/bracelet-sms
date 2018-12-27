package com.bracelet.sms.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bracelet.sms.R;
import com.bracelet.sms.app.action.AppAction;
import com.bracelet.sms.app.manager.BroadcastManager;
import com.bracelet.sms.ui.activity.HomeActivity;
import com.bracelet.sms.ui.base.BaseFragment;
import com.bracelet.sms.ui.presenter.DevicesFgPresenter;
import com.bracelet.sms.ui.presenter.view.IDevicesFgView;
import com.bracelet.sms.ui.utils.UIUtils;
import com.lqr.recyclerview.LQRRecyclerView;

import butterknife.BindView;

public class DevicesFragment extends BaseFragment<IDevicesFgView, DevicesFgPresenter> implements IDevicesFgView {

    @BindView(R.id.rvDevices)
    LQRRecyclerView mRvDevices;
    private TextView mFooterView;

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        mFooterView = new TextView(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2Px(50));
        mFooterView.setLayoutParams(params);
        mFooterView.setGravity(Gravity.CENTER);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadDeviceList();
    }

    @Override
    protected DevicesFgPresenter createPresenter() {
        return new DevicesFgPresenter((HomeActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_devices;
    }

    @Override
    protected void registerBroadcast() {
        super.registerBroadcast();
        BroadcastManager.getInstance(getActivity()).register(AppAction.UPDATE_BRACELET_LIST, new
                BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        mPresenter.loadDeviceList();
                    }
                });
    }

    @Override
    protected void unRegisterBroadcast() {
        super.unRegisterBroadcast();
        BroadcastManager.getInstance(getActivity()).unregister(AppAction.UPDATE_BRACELET_LIST);
    }

    @Override
    public LQRRecyclerView getRvContacts() {
        return mRvDevices;
    }

    @Override
    public TextView getFooterView() {
        return mFooterView;
    }
}
