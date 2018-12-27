package com.bracelet.sms.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.bracelet.sms.R;
import com.bracelet.sms.ui.adapter.Note;
import com.bracelet.sms.ui.adapter.RecyclerSwipeViewAdapter;
import com.bracelet.sms.ui.base.BaseActivity;
import com.bracelet.sms.ui.base.BasePresenter;
import com.lqr.recyclerview.LQRRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by Tim.WJ on 2018/12/25.
 */
public class ConversationActivity extends BaseActivity {

    @BindView(R.id.rv_conversation_list)
    LQRRecyclerView mRVConversationList;

    private String braceletName;
    private String braceletTelephne;

    private Note[] notes = {new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23 15:22"), new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23"),
            new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23"), new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23"),
            new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23"), new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23"),
            new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23"), new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23"),
            new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23"), new Note("标题1", "内容：4545454545454", "摘要：5555", "2017-5-23")};

    private List<Note> noteList = new ArrayList<>();

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

        myRecyclerViewAdapter = new RecyclerSwipeViewAdapter(this, noteList);
        mRVConversationList.setAdapter(myRecyclerViewAdapter);

    }

    @Override
    protected void initData() {
        super.initData();

        noteList.clear();
        for (int i = 0; i < 20; i++) {
            Random rand = new Random();
            int index = rand.nextInt(notes.length);
            noteList.add(notes[index]);
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
