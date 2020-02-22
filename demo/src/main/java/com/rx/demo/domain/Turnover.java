package com.rx.demo.domain;

import com.rx.demo.constant.CONSTANT;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "turnover")
public class Turnover extends BaseDomain {

    /**
     * 用户sid
     */
    private Long userSid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 变动金额
     */
    private Float money;

    /**
     * 本次余额
     */
    private Float balance;

    /**
     * 叙述
     */
    private String text;

    /**
     * 0：充值
     * 1：购买
     * 2：提现
     */
    private Integer type;

    public Long getUserSid() {
        return userSid;
    }

    public void setUserSid(Long userSid) {
        this.userSid = userSid;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
