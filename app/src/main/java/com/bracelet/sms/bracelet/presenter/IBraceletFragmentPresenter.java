package com.bracelet.sms.bracelet.presenter;

import com.bracelet.sms.bracelet.model.BraceletBean;

import java.util.List;

/**
 * @author :Tim.WJ
 * Created on 2019/1/8.
 */
public interface IBraceletFragmentPresenter {

    /**
     * 加载数据
     *
     * @param forceUpdate boolean
     */
    void loadModels(boolean forceUpdate);

}
