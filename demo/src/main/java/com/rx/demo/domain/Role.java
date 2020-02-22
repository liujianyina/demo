package com.rx.demo.domain;


import com.rx.demo.constant.CONSTANT;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "role")
public class Role extends BaseDomain {
    private static final long serialVersionUID = -3420835074245073690L;

    /**
     * 角色名
     */
    @Column(nullable = false, length = 30)
    private String name;

    /**
     * 权限
     */
    @Column(length = 30)
    private String permission;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = CONSTANT.TABLE_PREFIX + "role_resources",
            joinColumns = {@JoinColumn(name = "role_sid")},
            inverseJoinColumns = {@JoinColumn(name = "resources_sid")})
    private List<Resources> resourcesList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<Resources> getResourcesList() {
        return resourcesList;
    }

    public void setResourcesList(List<Resources> resourcesList) {
        this.resourcesList = resourcesList;
    }

    /**
     * 获取角色绑定的所有资源的sid
     *
     * @return
     */
    public List<Long> getResourceSids() {
        List<Long> result = new ArrayList<>();
        resourcesList.forEach(r -> result.add(r.getSid()));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Role o = (Role) obj;
        return o.getSid().equals(this.sid);
    }
}
