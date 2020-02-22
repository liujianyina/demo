package com.rx.demo.service;

import com.rx.demo.domain.Withdraw;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WithdrawService extends BaseService<Withdraw> {

    Page<Withdraw> withdrawList(int page, int limit, List<Integer> states);

}
