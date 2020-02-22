package com.rx.demo.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rx.demo.controller.BaseController;
import com.rx.demo.domain.User;
import com.rx.demo.domain.Withdraw;
import com.rx.demo.service.TurnoverService;
import com.rx.demo.service.UserService;
import com.rx.demo.service.WithdrawService;
import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.ShiroUtils;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/withdraw")
public class AppWithdrawController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TurnoverService turnoverService;

    @Autowired
    private WithdrawService withdrawService;


    @PostMapping("/withdraw_money")
    public AjaxResult withdrawMoney(){
        Long userSid = ShiroUtils.getCurrentUser().getSid();

        User user = userService.findOne(userSid);

        Float tmoney = turnoverService.currentTurnover(userSid);

        if (tmoney > user.getMoney() * 4){
            return AjaxResult.success(user.getMoney());
        }else {
            return AjaxResult.error("流水未达到80%，无法申请提现");
        }
    }

    @PostMapping("/withdraw_create")
    public AjaxResult withdrawCreate(@RequestBody String requestBody){

        JSONObject jsonObject = Utils.getRequestBody(requestBody);
        Float tixian = jsonObject.getFloat("tixian");
        Float shouxu = jsonObject.getFloat("shouxu");

        User user = userService.findOne(ShiroUtils.getCurrentUser().getSid());
        if (user.getMoney() > (tixian + shouxu)){

            user.setMoney(user.getMoney() - (tixian + shouxu));
            ShiroUtils.refresh(userService.save(user));

            Withdraw withdraw = new Withdraw();
            withdraw.setUserSid(ShiroUtils.getCurrentUser().getSid());
            withdraw.setUsername(jsonObject.getString("username"));
            withdraw.setBankName(jsonObject.getString("bankName"));
            withdraw.setCarNumber(jsonObject.getString("carNumber"));
            withdraw.setMoney(tixian);
            withdraw.setShouxu(shouxu);
            withdrawService.save(withdraw);

            return AjaxResult.success();
        }else {
            return AjaxResult.error("提现额度超过余额，提现失败");
        }

    }

}
