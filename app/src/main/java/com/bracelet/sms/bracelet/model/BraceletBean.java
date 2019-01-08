package com.bracelet.sms.bracelet.model;

import android.support.annotation.NonNull;
import org.litepal.crud.LitePalSupport;

/**
 * @program: bracelet-sms
 * @packageName: com.bracelet.sms.bracelet.model
 * @fileName: BraceletBean
 * @author: Tell.Tim
 * @date: 2019/1/8 18:26
 * @description:
 */
public class BraceletBean extends LitePalSupport implements Comparable<BraceletBean> {

    /**
     * 主键id
     */
    private int id;
    /**
     * 昵称
     */
    private String nikeName;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 是否绑定
     */
    private boolean binding;

    public BraceletBean(String nikeName, String telephone) {
        this.nikeName = nikeName;
        this.telephone = telephone;
    }

    public BraceletBean(String nikeName, String telephone, boolean binding) {
        this.nikeName = nikeName;
        this.telephone = telephone;
        this.binding = binding;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isBinding() {
        return binding;
    }

    public void setBinding(boolean binding) {
        this.binding = binding;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            BraceletBean braceletInfo = (BraceletBean) obj;
            return (getTelephone() != null && getTelephone().equals(braceletInfo.getTelephone()));
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(@NonNull BraceletBean o) {
        return this.getTelephone().compareTo(o.getTelephone());
    }
}
