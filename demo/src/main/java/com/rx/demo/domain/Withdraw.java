package com.rx.demo.domain;

import com.rx.demo.constant.CONSTANT;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "withdraw")
public class Withdraw extends BaseDomain {

    private Long userSid;

    private String username;

    private String bankName;

    private String carNumber;

    private Float money;

    private Float shouxu;

    /**
     * 0:发起申请
     * 1：通过
     * 2：拒绝
     */
    private Integer state = 0;


    private String msg;

    public Long getUserSid() {
        return userSid;
    }

    public void setUserSid(Long userSid) {
        this.userSid = userSid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Float getShouxu() {
        return shouxu;
    }

    public void setShouxu(Float shouxu) {
        this.shouxu = shouxu;
    }
}
