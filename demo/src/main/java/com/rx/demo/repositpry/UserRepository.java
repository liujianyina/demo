package com.rx.demo.repositpry;


import com.rx.demo.domain.User;

public interface UserRepository extends BaseRepository<User> {

    User findByUsername(String username);

}
