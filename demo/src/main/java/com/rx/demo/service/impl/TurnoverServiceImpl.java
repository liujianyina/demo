package com.rx.demo.service.impl;

import com.rx.demo.domain.Turnover;
import com.rx.demo.repositpry.TurnoverRepository;
import com.rx.demo.service.TurnoverService;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class TurnoverServiceImpl extends BaseServiceImpl<Turnover, TurnoverRepository> implements TurnoverService {

    private TurnoverRepository turnoverRepository;

    @Autowired
    @Override
    public void setRepository(TurnoverRepository repository) {
        this.turnoverRepository = repository;
        this.baseRepository = repository;
    }

    @Override
    public Page<Turnover> turnoverList(int page, int limit, Long userSid) {
        return turnoverRepository.findAll((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userSid"), userSid), PageRequest.of(page - 1, limit));
    }

    @Override
    public Page<Turnover> turnoverList(int page, int limit) {
        return turnoverRepository.findAll(PageRequest.of(page - 1, limit, Sort.Direction.DESC, "createTime"));
    }

    @Override
    public Float currentTurnover(Long userSid) {

        Float res = 0F;

        List<Turnover> turnovers = turnoverRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Predicate p1 = criteriaBuilder.equal(root.get("userSid"), userSid);
            Predicate p2 = criteriaBuilder.between(root.get("createTime"), Utils.initDateByDay(), Utils.endDateByDay());
            return criteriaBuilder.and(p1, p2);
        });

        for (Turnover t : turnovers){

            Float tmoney = t.getMoney();
            if (tmoney < 0){
                res -= tmoney;
            }else {
                res += tmoney;
            }
        }

        return res;
    }
}
