package com.bracelet.sms.bracelet.view;

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
import com.bracelet.sms.home.HomeActivity;
import com.bracelet.sms.base.BaseFragment;
import com.bracelet.sms.bracelet.presenter.BraceletsFgPresenter;
import com.bracelet.sms.bracelet.view.IBraceletsFgView;
import com.bracelet.sms.utils.UIUtils;
import com.lqr.recyclerview.LQRRecyclerView;

import butterknife.BindView;

public class BraceletsFragment extends BaseFragment<IBraceletsFgView, BraceletsFgPresenter> implements
    IBraceletsFgView {

    @BindView(R.id.rvBracelets)
    LQRRecyclerView mRvBracelets;
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
        mPresenter.loadBraceletList();
    }

    @Override
    protected BraceletsFgPresenter createPresenter() {
        return new BraceletsFgPresenter((HomeActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_bracelets;
    }

    @Override
    protected void registerBroadcast() {
        super.registerBroadcast();
        BroadcastManager.getInstance(getActivity()).register(AppAction.UPDATE_BRACELET_LIST, new
                BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        mPresenter.loadBraceletList();
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
        return mRvBracelets;
    }

    @Override
    public TextView getFooterView() {
        return mFooterView;
    }
}
