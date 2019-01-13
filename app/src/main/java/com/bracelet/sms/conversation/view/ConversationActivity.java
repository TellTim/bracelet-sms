package com.bracelet.sms.conversation.view;

import android.content.Intent;
import android.graphics.Path;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.bracelet.protocol.model.Operation;
import com.bracelet.sms.R;
import com.bracelet.sms.base.BaseActivity;
import com.bracelet.sms.base.BasePresenter;
import com.bracelet.sms.base.IBaseCallback;
import com.bracelet.sms.conversation.model.OperationModel;
import com.lqr.recyclerview.LQRRecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        if (OperationModel.getInstance().getOperationContent().size() == 0) {
            OperationModel.getInstance().reQueryAllOperation(new IBaseCallback<List<Operation>>() {
                @Override
                public void onSuccess(List<Operation> data) {
                    for (int i = 0;i<data.size();i++){
                        cmdList.add(data.get(i).getContent());
                    }
                }

                @Override
                public void onFailure(int failureCode) {
                    // TODO
                }

                @Override
                public void onError(int errorCode) {
                    // TODO
                }

                @Override
                public void onComplete() {

                }
            });
        } else {
            for (int i = 0; i < OperationModel.getInstance().getOperationContent().size(); i++) {
                cmdList.add(OperationModel.getInstance().getOperationContent().get(i));
            }
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
