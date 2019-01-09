package com.bracelet.sms.bracelet.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bracelet.sms.R;
import com.bracelet.sms.app.action.AppAction;
import com.bracelet.sms.app.manager.BroadcastManager;
import com.bracelet.sms.base.BaseFragment;
import com.bracelet.sms.bracelet.model.BraceletBean;
import com.bracelet.sms.bracelet.presenter.BraceletsFragmentPresenter;
import com.bracelet.sms.conversation.view.ConversationActivity;
import com.bracelet.sms.utils.UIUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRHeaderAndFooterAdapter;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.recyclerview.LQRRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author admin
 */
public class BraceletsFragment extends BaseFragment<IBraceletsFragmentView, BraceletsFragmentPresenter> implements
        IBraceletsFragmentView {

    @BindView(R.id.rvBracelets)
    LQRRecyclerView mRvBracelets;

    private List<BraceletBean> mData = new ArrayList<>();
    private LQRHeaderAndFooterAdapter mListAdapter;
    private static int deviceListColors[] = {R.color.device_color_1, R.color.device_color_2,
            R.color.device_color_3, R.color.device_color_4, R.color.device_color_5};
    private TextView mFooterView;

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);

        mFooterView = new TextView(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils
                .dip2Px(50));
        mFooterView.setLayoutParams(params);
        mFooterView.setGravity(Gravity.CENTER);

        //初始化适配器
        LQRAdapterForRecyclerView adapter = new LQRAdapterForRecyclerView<BraceletBean>(getContext(), mData,
                R.layout.item_bracelet) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, BraceletBean item, int position) {
                helper.setText(R.id.tv_bracelet_name, item.getNikeName());
                CardView cardView = helper.getConvertView().findViewById(R.id.item_container);
                cardView.setCardBackgroundColor(getContext().getColor(deviceListColors[position % 5]));
            }
        };
        adapter.addFooterView(mFooterView);
        mListAdapter = adapter.getHeaderAndFooterAdapter();
        mRvBracelets.setAdapter(mListAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadModels(false);
    }

    @Override
    protected void initListener() {
        super.initListener();
        ((LQRAdapterForRecyclerView) mListAdapter.getInnerAdapter()).setOnItemClickListener(
                (lqrViewHolder, viewGroup, view, i) -> {
                    Intent intent = new Intent(getContext(), ConversationActivity.class);
                    intent.putExtra("name", mData.get(i).getNikeName());
                    intent.putExtra("telephone", mData.get(i).getTelephone());
                    //getContext().jumpToActivity(intent);
                    UIUtils.showToast("点击了第" + i + "个控件" + " name is " + mData.get(i).getNikeName() + " 号码是" +
                            mData.get(i).getTelephone());
                });
    }

    @Override
    protected BraceletsFragmentPresenter createPresenter() {
        return new BraceletsFragmentPresenter();
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
                        mPresenter.loadModels(true);
                    }
                });
    }

    @Override
    protected void unRegisterBroadcast() {
        super.unRegisterBroadcast();
        BroadcastManager.getInstance(getActivity()).unregister(AppAction.UPDATE_BRACELET_LIST);
    }

    @Override
    public void showAllModels(List<BraceletBean> beans) {
        resetAdapter(beans);
    }

    @Override
    public void showNoModel() {
        showToast(UIUtils.getString(R.string.str_empty));
    }

    /**
     * 重置适配器中的数据
     *
     * @param data
     */
    private void resetAdapter(List<BraceletBean> data) {
        mData.clear();
        mData.addAll(data);
        mListAdapter.notifyDataSetChanged();
    }
}
