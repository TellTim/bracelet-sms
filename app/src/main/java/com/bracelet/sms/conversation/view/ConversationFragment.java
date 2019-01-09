package com.bracelet.sms.conversation.view;

import com.bracelet.sms.R;
import com.bracelet.sms.base.BaseFragment;
import com.bracelet.sms.conversation.presenter.ConversationFgPresenter;
import com.bracelet.sms.conversation.view.IConversationFgView;

/**
 * @author tell.tim
 */
public class ConversationFragment extends BaseFragment<IConversationFgView, ConversationFgPresenter> implements IConversationFgView {
    @Override
    protected ConversationFgPresenter createPresenter() {
        return new ConversationFgPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_conversations;
    }
}
