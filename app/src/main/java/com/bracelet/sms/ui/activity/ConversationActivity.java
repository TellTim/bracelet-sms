package com.bracelet.sms.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.bracelet.sms.R;
import com.bracelet.sms.ui.adapter.RecyclerSwipeViewAdapter;
import com.bracelet.sms.ui.base.BaseActivity;
import com.bracelet.sms.ui.base.BasePresenter;
import com.lqr.recyclerview.LQRRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Tim.WJ on 2018/12/25.
 */
public class ConversationActivity extends BaseActivity {

    @BindView(R.id.rv_conversation_list)
    LQRRecyclerView mRVConversationList;

    private String braceletName;
    private String braceletTelephne;

    private ArrayList<String> cmdList;
    private RecyclerSwipeViewAdapter myRecyclerViewAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        braceletName = intent.getStringExtra("name");
        braceletTelephne = intent.getStringExtra("telephone");
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle(braceletName);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        mRVConversationList.setLayoutManager(layoutManager);
        myRecyclerViewAdapter = new RecyclerSwipeViewAdapter(this, cmdList);
        mRVConversationList.setAdapter(myRecyclerViewAdapter);

    }

    @Override
    protected void initData() {
        super.initData();

        if (cmdList == null) {
            cmdList = new ArrayList<>();
        }
        for (int i = 0; i < 6; i++) {
            cmdList.add(braceletTelephne);
        }

    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void registerBroadcast() {
        super.registerBroadcast();
    }

    @Override
    protected void unRegisterBroadcast() {
        super.unRegisterBroadcast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_conversation;
    }
}
