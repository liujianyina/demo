package com.rx.demo.repositpry;

import com.rx.demo.domain.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findAllBySuccessAndUserSid(Integer success,Long userSid);

}
