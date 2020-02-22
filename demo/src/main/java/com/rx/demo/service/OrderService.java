package com.rx.demo.service;

import com.rx.demo.constant.STATUS;
import com.rx.demo.domain.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OrderService extends BaseService<Order> {

    Map<String, List<Order>> orderMap(Long userSid);

    Page<Order> orderList(int page,int limit);

    Page<Order> orderList(int page, int limit, STATUS.SUCCESS success,STATUS.DEAL deal);

    Page<Order> orderList(int page,int limit,Long userSid);

}
