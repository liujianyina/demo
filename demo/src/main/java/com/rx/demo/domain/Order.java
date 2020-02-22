package com.rx.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rx.demo.constant.CONSTANT;
import com.rx.demo.constant.STATUS;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "order")
public class Order extends BaseDomain {

    private Long userSid;

    /**
     * 买家username
     */
    private String username;

    /**
     * 产品
     */
    private String productName;

    /**
     * 买的方向
     */
    private Integer inx;

    /**
     * 买入时候价格
     */
    private Float price;

    /**
     * 订单金额
     */
    private Float orderMoney;

    /**
     * 实际盈亏
     */
    private Float money;

    /**
     * 间隔时间
     */
    private Integer time;

    /**
     * 到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 0:亏损
     * 1：平仓
     * 2：盈利
     * 默认亏损
     */
    private Integer state = STATUS.ORDER_STATE.LOSE.getState();

    /**
     * 0: 未处理
     * 1: 手动处理
     */
    private Integer deal = STATUS.DEAL.NOT_DEAL.getDeal();

    /**
     * 0:未完成
     * 1:已完成
     */
    private Integer success = STATUS.SUCCESS.NOT_SUCCESS.getSuccess();

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getInx() {
        return inx;
    }

    public void setInx(Integer inx) {
        this.inx = inx;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Float orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
