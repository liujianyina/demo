package com.rx.demo.service;


import com.rx.demo.domain.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {

    List<Role> userRegister();

}
