package com.bracelet.sms.conversation.presenter;

import com.bracelet.sms.base.BasePresenter;
import com.bracelet.sms.conversation.model.IOperationModel;
import com.bracelet.sms.conversation.model.OperationModel;
import com.bracelet.sms.conversation.view.IConversationFragmentView;

public class ConversationFragmentPresenter extends BasePresenter<IConversationFragmentView> {

    private IOperationModel operationModel;

    public ConversationFragmentPresenter() {
        /*operationModel = new OperationModel();
        operationModel.getOperations();*/
    }
}
