package com.rx.demo.service.impl;

import com.rx.demo.constant.STATUS;
import com.rx.demo.domain.Order;
import com.rx.demo.repositpry.OrderRepository;
import com.rx.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRepository> implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    @Override
    public void setRepository(OrderRepository repository) {
        this.orderRepository = repository;
        this.baseRepository = repository;
    }

    @Override
    public Map<String, List<Order>> orderMap(Long userSid) {

        Map<String, List<Order>> map = new HashMap<>();
        map.put("NOT_SUCCESS",orderRepository.findAllBySuccessAndUserSid(STATUS.SUCCESS.NOT_SUCCESS.getSuccess(),userSid));
        map.put("ALREADY_SUCCESS",orderRepository.findAllBySuccessAndUserSid(STATUS.SUCCESS.ALREADY_SUCCESS.getSuccess(),userSid));

        return map;
    }

    @Override
    public Page<Order> orderList(int page, int limit) {
        return orderRepository.findAll(PageRequest.of(page -1,limit, Sort.Direction.DESC,"createTime"));
    }

    @Override
    public Page<Order> orderList(int page, int limit, STATUS.SUCCESS success, STATUS.DEAL deal) {
        return orderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {

            Predicate p1 = criteriaBuilder.equal(root.get("success"),success.getSuccess());
            Predicate p2 = criteriaBuilder.equal(root.get("deal"),deal.getDeal());

            return criteriaBuilder.and(p1,p2);
        },PageRequest.of(page-1,limit));
    }

    @Override
    public Page<Order> orderList(int page, int limit, Long userSid) {
        return orderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userSid"),userSid),PageRequest.of(page -1,limit));
    }
}
