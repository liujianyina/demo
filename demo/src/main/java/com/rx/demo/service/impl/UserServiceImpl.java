package com.rx.demo.service.impl;

import com.rx.demo.constant.CONSTANT;
import com.rx.demo.domain.User;
import com.rx.demo.repositpry.UserRepository;
import com.rx.demo.service.UserService;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

    private UserRepository userRepository;


    @Autowired
    @Override
    public void setRepository(UserRepository repository) {
        this.userRepository = repository;
        this.baseRepository = repository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> userList(int page, int limit, List<Long> roleSids) {

        if (Utils.isEmpty(roleSids)){
            return userRepository.findAll(PageRequest.of(page - 1,limit));
        }else {
            String sql = "SELECT user_sid FROM " + CONSTANT.TABLE_PREFIX + "user_role WHERE role_sid in (:roleSids)";
            Map<String,Object> map = new HashMap<>();
            map.put("roleSids",roleSids);
            List userSids = this.runSqlForQuery(sql,map).getResultList();
            return userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> root.get("sid").in(userSids),PageRequest.of(page -1,limit));
        }
    }

    @Override
    public Page<User> userList(int page, int limit, Long createSid) {
        return userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("createSid"),createSid),PageRequest.of(page - 1,limit));
    }
}
