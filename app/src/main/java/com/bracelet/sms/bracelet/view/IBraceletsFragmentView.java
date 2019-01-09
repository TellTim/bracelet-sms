package com.bracelet.sms.bracelet.view;

import android.widget.TextView;

import com.bracelet.sms.base.IBaseView;
import com.bracelet.sms.bracelet.model.BraceletBean;
import com.lqr.recyclerview.LQRRecyclerView;

import java.util.List;

public interface IBraceletsFragmentView extends IBaseView {
    /**
     * 因为P中去访问M的方法中，一般都有回调方法，为了避免在Activity中直接showAllModels，而需要去实现M中的回调方法，现只需要加载数据然后渲染页面
     *
     * @param beans List<ModelBean>
     */
    void showAllModels(List<BraceletBean> beans);

    /**
     * 页面显示无数据
     */
    void showNoModel();
}
