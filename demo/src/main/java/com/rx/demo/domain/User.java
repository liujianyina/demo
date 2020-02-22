package com.rx.demo.domain;

import com.rx.demo.constant.CONSTANT;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Entity
@Table(name = CONSTANT.TABLE_PREFIX + "user")
public class User extends BaseDomain {
    private static final long serialVersionUID = 2791757961950650026L;

    @NotNull(message = "用户名不能为空")
    @Column(nullable = false, updatable = false, unique = true, length = 30)
    private String username;

    @NotNull(message = "密码不能为空")
    @Column(nullable = false, length = 32)
    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 上级sid
     */
    private Long createSid;

    private Float money = 0F;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = CONSTANT.TABLE_PREFIX + "user_role", joinColumns = {@JoinColumn(name = "user_sid")}, inverseJoinColumns = {@JoinColumn(name = "role_sid")})
    private List<Role> roleList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCreateSid() {
        return createSid;
    }

    public void setCreateSid(Long createSid) {
        this.createSid = createSid;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    /**
     * 取得用户所有可访问的菜单 sid
     *
     * @return
     */
    public Set<Long> getResourceSids() {
        Set<Long> sids = new HashSet<>();
        if (null != roleList && !roleList.isEmpty()) {
            for (Role role : roleList) {
                if (role.getStatus() == 1) {
                    sids.addAll(role.getResourceSids());
                }
            }
        }
        return sids;
    }


}

