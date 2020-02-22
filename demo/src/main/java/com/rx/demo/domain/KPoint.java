package com.rx.demo.domain;

import com.rx.demo.constant.CONSTANT;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "kpoint")
public class KPoint extends BaseDomain {

    private Float low;

    private Float height;

    private Float close;

    private Float open;

    private String flag;

    private Long productSid;

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Float getOpen() {
        return open;
    }

    public void setOpen(Float open) {
        this.open = open;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getProductSid() {
        return productSid;
    }

    public void setProductSid(Long productSid) {
        this.productSid = productSid;
    }
}
