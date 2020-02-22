package com.rx.demo.domain;

import com.rx.demo.constant.CONSTANT;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "product")
public class Product extends BaseDomain {

    /**
     * 产品名字
     */
    public String name;

    /**
     * 字母符号
     */
    public String symbol;

    /**
     * 最小值
     */
    private Float min;

    /**
     * 最大值
     */
    private Float max;

    private String filename;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
