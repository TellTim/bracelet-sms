package com.bracelet.sms.bracelet.presenter;

import android.content.Intent;
import android.support.v7.widget.CardView;

import com.bracelet.sms.R;
import com.bracelet.sms.bracelet.model.BraceletBean;
import com.bracelet.sms.ui.activity.ConversationActivity;
import com.bracelet.sms.base.BaseActivity;
import com.bracelet.sms.base.BasePresenter;
import com.bracelet.sms.bracelet.view.IBraceletsFgView;
import com.bracelet.sms.utils.UIUtils;
import com.bracelet.sms.utils.ULog;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRHeaderAndFooterAdapter;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BraceletsFgPresenter extends BasePresenter<IBraceletsFgView> {

    private static final String TAG = "BraceletsFgPresenter";
    private List<BraceletBean> mData = new ArrayList<>();
    private LQRHeaderAndFooterAdapter mAdapter;
    private static int deviceListColors[] = {R.color.device_color_1, R.color.device_color_2,
            R.color.device_color_3, R.color.device_color_4, R.color.device_color_5};

    public BraceletsFgPresenter(BaseActivity context) {
        super(context);
    }

    public void loadBraceletList() {
        setAdapter();
        loadData();
    }

    private void loadData() {
        Observable.just(DBManager.getInstance().getBracelets())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(friends -> {
                    if (friends != null && friends.size() > 0) {
                        mData.clear();
                        mData.addAll(friends);
                        getView().getFooterView()
                                .setText(UIUtils.getString(R.string.count_of_bracelets, mData.size()));
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }, this::loadError);
    }

    private void loadError(Throwable throwable) {
        ULog.e(TAG, "加载手环异常", throwable);
        UIUtils.showToast("加载手环异常");
    }

    private void setAdapter() {
        if (mAdapter == null) {
            LQRAdapterForRecyclerView adapter
                    = new LQRAdapterForRecyclerView<Bracelet>(mContext, mData, R.layout.item_bracelet) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, Bracelet item, int position) {
                    helper.setText(R.id.tv_bracelet_name, item.getNikeName());
                    CardView cardView = helper.getConvertView().findViewById(R.id.item_container);
                    cardView.setCardBackgroundColor(mContext.getColor(deviceListColors[position % 5]));
                }
            };
            adapter.addFooterView(getView().getFooterView());
            mAdapter = adapter.getHeaderAndFooterAdapter();
            getView().getRvContacts().setAdapter(mAdapter);
        }
        ((LQRAdapterForRecyclerView) mAdapter.getInnerAdapter()).setOnItemClickListener(
                (lqrViewHolder, viewGroup, view, i) -> {
                    Intent intent = new Intent(mContext, ConversationActivity.class);
                    intent.putExtra("name", mData.get(i).getNikeName());//-1是因为有头部
                    intent.putExtra("telephone", mData.get(i).getTelephone());//-1是因为有头部
                    mContext.jumpToActivity(intent);
                    UIUtils.showToast("点击了第" + i + "个控件" + " name is " + mData.get(i).getNikeName() + " 号码是" +
                            mData.get(i).getTelephone());
                });
    }
}
