package com.rx.demo.service.impl;

import com.rx.demo.domain.Role;
import com.rx.demo.repositpry.RoleRepository;
import com.rx.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleRepository> implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    @Override
    public void setRepository(RoleRepository repository) {
        this.roleRepository = repository;
        this.baseRepository = repository;
    }


    @Override
    public List<Role> userRegister() {
        List<Role> roles = new ArrayList<>();
        roles.add(this.findOne(3L));
        return roles;
    }
}