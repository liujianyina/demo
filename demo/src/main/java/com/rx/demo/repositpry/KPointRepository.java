package com.rx.demo.repositpry;

import com.rx.demo.domain.KPoint;

import java.util.List;

public interface KPointRepository extends BaseRepository<KPoint> {

    List<KPoint> findAllByProductSid(Long productSid);

}
