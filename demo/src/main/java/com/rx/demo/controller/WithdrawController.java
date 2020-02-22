package com.rx.demo.controller;

import com.rx.demo.constant.CONSTANT;
import com.rx.demo.constant.STATUS;
import com.rx.demo.domain.BaseDomain;
import com.rx.demo.domain.Turnover;
import com.rx.demo.domain.User;
import com.rx.demo.domain.Withdraw;
import com.rx.demo.service.TurnoverService;
import com.rx.demo.service.UserService;
import com.rx.demo.service.WithdrawService;
import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseDomain {

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private UserService userService;

    @Autowired
    private TurnoverService turnoverService;

    @GetMapping("/withdraw_list")
    public String withdrawList() {
        return "/withdraw/withdraw_list";
    }

    @ResponseBody
    @PostMapping("/withdraw_list")
    public AjaxResult withdrawList(int page, int limit, @RequestParam(name = "states[]", required = false) List<Integer> states) {
        Page<Withdraw> withdraws = withdrawService.withdrawList(page, limit, states);
        return AjaxResult.success(withdraws.getTotalElements(), withdraws.getContent());
    }

    @ResponseBody
    @PostMapping("/update_state")
    public AjaxResult withdrawList(Long withdrawSid, Integer state) {
        Withdraw withdraw = withdrawService.findOne(withdrawSid);
        withdraw.setState(state);
        withdrawService.save(withdraw);
        User user = userService.findOne(ShiroUtils.getCurrentUser().getSid());

        if (STATUS.WITHDRAW.PASS.getState().equals(state)) {  //如果是通过了
            Turnover turnover = new Turnover();
            turnover.setUserSid(user.getSid());
            turnover.setUsername(user.getUsername());
            turnover.setMoney(-(withdraw.getMoney() + withdraw.getShouxu()));
            turnover.setText(String.format(CONSTANT.TURNOVER.TI, withdraw.getMoney(), withdraw.getShouxu()));
            turnoverService.save(turnover);
        } else {
            user.setMoney(withdraw.getMoney() + withdraw.getShouxu());
            ShiroUtils.refresh(userService.save(user));
        }
        return AjaxResult.success();
    }

}
