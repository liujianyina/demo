package com.rx.demo.service.impl;

import com.rx.demo.domain.KPoint;
import com.rx.demo.repositpry.KPointRepository;
import com.rx.demo.service.KPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KPointServiceImpl extends BaseServiceImpl<KPoint,KPointRepository> implements KPointService {

    private KPointRepository KPointRepository;

    @Autowired
    @Override
    public void setRepository(KPointRepository repository) {
        this.KPointRepository = repository;
        this.baseRepository = repository;
    }

    @Override
    public List<KPoint> findAllByProductSid(Long productSid) {
        return KPointRepository.findAllByProductSid(productSid);
    }
}
