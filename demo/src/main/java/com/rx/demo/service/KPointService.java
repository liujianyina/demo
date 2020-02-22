package com.rx.demo.service;

import com.rx.demo.domain.KPoint;

import java.util.List;

public interface KPointService extends BaseService<KPoint>{

    List<KPoint> findAllByProductSid(Long productSid);

}
