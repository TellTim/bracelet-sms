package com.bracelet.sms.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bracelet.sms.R;
import com.bracelet.sms.widget.CustomDialog;
import com.bracelet.sms.utils.UIUtils;

import butterknife.ButterKnife;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements IBaseView {

    protected T mPresenter;
    private CustomDialog mDialogWaiting;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        View rootView = inflater.inflate(provideContentViewId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        registerBroadcast();
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterBroadcast();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected void init() {

    }

    protected void initView(View rootView) {
    }

    protected void initData() {

    }

    protected void initListener() {

    }

    protected void registerBroadcast() {
    }

    protected void unRegisterBroadcast() {
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    /**
     * 显示等待提示框
     */
    @Override
    public Dialog showWaitingDialog(String tip) {
        hideWaitingDialog();
        View view = View.inflate(getContext(), R.layout.layout_dialog_waiting, null);
        if (!TextUtils.isEmpty(tip)) {
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        }
        mDialogWaiting = new CustomDialog(getContext(), view, R.style.CustomDialog);
        mDialogWaiting.show();
        mDialogWaiting.setCancelable(false);
        return mDialogWaiting;
    }

    /**
     * 隐藏等待提示框
     */
    @Override
    public void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }

    /**
     * 显示提示
     *
     * @param msg String
     */
    @Override
    public void showToast(String msg) {
        UIUtils.showToast(msg);
    }

    /**
     * 显示请求错误提示
     */
    @Override
    public void showErr(int errorCode) {

    }
}
