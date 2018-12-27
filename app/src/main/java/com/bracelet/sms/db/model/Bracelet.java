package com.bracelet.sms.db.model;

import android.support.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

/**
 * 手环信息表
 */
public class Bracelet extends LitePalSupport implements Comparable<Bracelet> {

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

    public Bracelet(String nikeName, String telephone) {
        this.nikeName = nikeName;
        this.telephone = telephone;
    }

    public Bracelet(String nikeName, String telephone, boolean binding) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            Bracelet braceletInfo = (Bracelet) obj;
            return (getTelephone() != null && getTelephone().equals(braceletInfo.getTelephone()));
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(@NonNull Bracelet o) {
        return this.getTelephone().compareTo(o.getTelephone());
    }
}
