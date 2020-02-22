package com.rx.demo.domain;

import com.rx.demo.constant.CONSTANT;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "properties")
public class Properties extends BaseDomain{

    private String keey;

    private String value;

    public String getKeey() {
        return keey;
    }

    public void setKeey(String keey) {
        this.keey = keey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
