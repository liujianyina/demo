package com.rx.demo.service;

import com.rx.demo.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends BaseService<User> {

    User findByUsername(String username);

    Page<User> userList(int page, int limit, List<Long> roleSids);

    Page<User> userList(int page, int limit, Long createSid);

}
