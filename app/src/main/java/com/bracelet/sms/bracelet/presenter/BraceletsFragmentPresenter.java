package com.bracelet.sms.bracelet.presenter;

import com.bracelet.sms.base.BasePresenter;
import com.bracelet.sms.base.IBaseCallback;
import com.bracelet.sms.bracelet.model.BraceletBean;
import com.bracelet.sms.bracelet.model.BraceletModel;
import com.bracelet.sms.bracelet.model.IBraceletModel;
import com.bracelet.sms.bracelet.view.IBraceletsFragmentView;
import com.lqr.adapter.LQRHeaderAndFooterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tell.tim
 */
public class BraceletsFragmentPresenter extends BasePresenter<IBraceletsFragmentView> implements
        IBraceletFragmentPresenter {

    private static final String TAG = "BraceletsFragmentPresenter";

    private IBraceletModel braceletModel;

    /**
     * 是否第一次加载
     */
    private boolean mFirstLoad = true;

    public BraceletsFragmentPresenter() {
        this.braceletModel = new BraceletModel();
    }

    @Override
    public void loadModels(boolean forceUpdate) {
        loadModels(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadModels(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            getView().showWaitingDialog("正在加载数据");
        }

        if (forceUpdate) {
            //此处用来清理缓存
        }

        braceletModel.queryAllBracelets(new IBaseCallback<List<BraceletBean>>() {
            @Override
            public void onSuccess(List<BraceletBean> data) {
                if (showLoadingUI) {
                    getView().hideWaitingDialog();
                }
                //调用V中的方法,来显示数据
                if (data != null) {
                    getView().showAllModels(data);
                } else {
                    getView().showNoModel();
                }
            }

            @Override
            public void onFailure(int failureCode) {
                if (showLoadingUI) {
                    getView().hideWaitingDialog();
                }
            }

            @Override
            public void onError(int errorCode) {
                if (showLoadingUI) {
                    getView().hideWaitingDialog();
                }
            }

            @Override
            public void onComplete() {
                if (showLoadingUI) {
                    getView().hideWaitingDialog();
                }
            }
        });
    }
}
