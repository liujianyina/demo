package com.rx.demo.domain;


import com.rx.demo.constant.CONSTANT;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "resources")
public class Resources extends BaseDomain implements Comparable<Resources> {
    private static final long serialVersionUID = 757868474516113072L;

    /**
     * 菜单名称
     */
    @Column(nullable = false, length = 30)
    private String cname;

    /**
     * 菜单链接
     */
    @Column(length = 60)
    private String url;

    /**
     * 父级sid
     */
    @Column
    private Long parentSid;


    /**
     * 1：显示 0：不显示
     * 是否需要在左侧菜单栏显示出来
     */
    private Integer display;

    /**
     * 资源类型
     */
    private Integer type;


    @Transient
    private List<Resources> children = new ArrayList<>();

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentSid() {
        return parentSid;
    }

    public void setParentSid(Long parentSid) {
        this.parentSid = parentSid;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Resources> getChildren() {
        return children;
    }

    public void setChildren(List<Resources> children) {
        this.children = children;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  getSid().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Resources)) {
            return false;
        }
        Resources other = (Resources) obj;
        if (getSid() == null) {
            if (other.getSid() != null) {
                return false;
            }
        } else if (!getSid().equals(other.getSid())) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Resources o) {
        return this.sid.compareTo(o.getSid());
    }
}
