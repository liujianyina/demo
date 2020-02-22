package com.rx.demo.service;

import com.rx.demo.domain.Turnover;
import org.springframework.data.domain.Page;

public interface TurnoverService extends BaseService<Turnover>{

    Page<Turnover>  turnoverList(int page,int limit,Long userSid);

    Page<Turnover>  turnoverList(int page,int limit);

    Float currentTurnover(Long userSid);

}
