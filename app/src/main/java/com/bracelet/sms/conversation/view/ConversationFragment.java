package com.bracelet.sms.conversation.view;

import com.bracelet.sms.R;
import com.bracelet.sms.base.BaseFragment;
import com.bracelet.sms.conversation.presenter.ConversationFragmentPresenter;

/**
 * @author tell.tim
 */
public class ConversationFragment extends BaseFragment<IConversationFragmentView, ConversationFragmentPresenter> implements IConversationFragmentView {
    @Override
    protected ConversationFragmentPresenter createPresenter() {
        return new ConversationFragmentPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_conversations;
    }
}
