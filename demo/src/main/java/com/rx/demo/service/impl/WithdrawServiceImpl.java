package com.rx.demo.service.impl;

import com.rx.demo.domain.Withdraw;
import com.rx.demo.repositpry.WithdrawRepository;
import com.rx.demo.service.WithdrawService;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawServiceImpl extends BaseServiceImpl<Withdraw, WithdrawRepository> implements WithdrawService {

    private WithdrawRepository withdrawRepository;

    @Autowired
    @Override
    public void setRepository(WithdrawRepository repository) {
        this.withdrawRepository = repository;
        this.baseRepository = repository;
    }

    @Override
    public Page<Withdraw> withdrawList(int page, int limit, List<Integer> states) {

        if (Utils.isEmpty(states)){
            return withdrawRepository.findAll(PageRequest.of(page - 1,limit));
        }else {
            return withdrawRepository.findAll((root, criteriaQuery, criteriaBuilder) -> root.get("state").in(states),PageRequest.of(page - 1,limit));
        }
    }
}
