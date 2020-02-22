package com.rx.demo.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rx.demo.controller.BaseController;
import com.rx.demo.domain.Order;
import com.rx.demo.domain.User;
import com.rx.demo.service.OrderService;
import com.rx.demo.service.UserService;
import com.rx.demo.task.DealOrder;
import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.ShiroUtils;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/order")
public class AppOrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/create_order")
    public AjaxResult createOrder(@RequestBody String requestBody){
        JSONObject jsonObject = Utils.getRequestBody(requestBody);

        Float money = jsonObject.getFloat("money");

        User user = userService.findOne(ShiroUtils.getCurrentUser().getSid());

        if (user.getMoney() < money){
            return AjaxResult.error("账户余额不足，请先充值");
        }

        Order order = new Order();
        order.setUserSid(user.getSid());
        order.setUsername(user.getUsername());
        order.setProductName(jsonObject.getString("name"));
        order.setInx(jsonObject.getInteger("inx"));
        order.setPrice(jsonObject.getFloat("price"));
        order.setOrderMoney(money);
        Integer time = jsonObject.getInteger("time");
        order.setTime(time);
        order.setEndTime(Utils.afterMinute(time));

        Order res = orderService.save(order);

        DealOrder.deal(res.getSid(),time);

        user.setMoney(user.getMoney() - money);
        ShiroUtils.refresh(userService.save(user));

        return AjaxResult.success(res);
    }

    @PostMapping("/order_list")
    public AjaxResult orderList(){
        return AjaxResult.success(orderService.orderMap(ShiroUtils.getCurrentUser().getSid()));
    }

    @PostMapping("/order_info")
    public AjaxResult orderInfo(@RequestBody String requestBody){
        JSONObject jsonObject = Utils.getRequestBody(requestBody);
        Long sid = jsonObject.getLong("sid");
        Order order = null;
        do {
            order = orderService.findOne(sid);
            if (Utils.isNull(order.getMoney())){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }while (Utils.isNull(order.getMoney()));
        return AjaxResult.success(order);
    }

}
