package com.bracelet.sms.db.model;

import android.support.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

/**
 * 机主信息表
 */
public class Owner extends LitePalSupport implements Comparable<Owner> {

    private String nikeName;
    private String telephone;

    public Owner(String nikeName, String telephone) {
        this.nikeName = nikeName;
        this.telephone = telephone;
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

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            Owner owner = (Owner) obj;
            return (getTelephone() != null && getTelephone().equals(owner.getTelephone()));
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(@NonNull Owner o) {
        return this.getTelephone().compareTo(o.getTelephone());
    }
}
