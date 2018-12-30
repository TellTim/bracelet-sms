package com.bracelet.sms.ui.fragment;

import com.bracelet.sms.R;
import com.bracelet.sms.ui.activity.HomeActivity;
import com.bracelet.sms.ui.base.BaseFragment;
import com.bracelet.sms.ui.presenter.ConversationFgPresenter;
import com.bracelet.sms.ui.presenter.view.IConversationFgView;

public class ConversationFragment extends BaseFragment<IConversationFgView, ConversationFgPresenter> implements IConversationFgView {
    @Override
    protected ConversationFgPresenter createPresenter() {
        return new ConversationFgPresenter((HomeActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_conversations;
    }
}
