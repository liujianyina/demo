package com.rx.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rx.demo.utils.Utils;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;


@MappedSuperclass
public abstract class BaseDomain implements BaseDomainInterface {
    private static final long serialVersionUID = 4302749702280530401L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false, updatable = false)
    protected Long sid;

    /**
     * 创建时间
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time", nullable = false, updatable = false)
    protected Date createTime;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time", nullable = false)
    protected Date updateTime;


    /**
     * 通用状态：-1.已删除；0.无效；1.有效
     */
    @Column(nullable = false)
    protected int status = 1;

    public BaseDomain() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public Map<String, Object> toMap() {
        return Utils.toMap(this, null, null);
    }

    @Override
    public Map<String, Object> toMapWithInclude(String[] fields) {
        return Utils.toMap(this, fields, null);
    }

    @Override
    public Map<String, Object> toMapWithExclude(String[] fields) {
        return Utils.toMap(this, null, fields);
    }

    /**
     * 取得实体对象对应的表名
     *
     * @return
     */
    public String getTableName() {
        Table ann = this.getClass().getAnnotation(Table.class);
        if (null != ann) {
            if (!Utils.isEmpty(ann.name())) {
                return ann.name();
            }
        }
        return "";
    }

    /**
     * 取得实体类名
     *
     * @return
     */
    public String getClsName() {
        return this.getClass().getSimpleName();
    }


}
