package com.rx.demo.controller;

import com.rx.demo.constant.STATUS;
import com.rx.demo.domain.Order;
import com.rx.demo.service.OrderService;
import com.rx.demo.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/order")
@Controller
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order_list")
    public String orderList() {
        return "/order/order_list";
    }


    /**
     * 所有的订单信息
     *
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @PostMapping("/order_list_all")
    public AjaxResult orderListAll(int page, int limit) {
        Page<Order> orders = orderService.orderList(page, limit);
        return AjaxResult.success(orders.getTotalElements(), orders.getContent());
    }

    /**
     * 未处理的订单信息
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/order_list_not_deal")
    public AjaxResult orderListNotDeal(int page,int limit) {
        Page<Order> orders = orderService.orderList(page,limit, STATUS.SUCCESS.NOT_SUCCESS, STATUS.DEAL.NOT_DEAL);
        return AjaxResult.success(orders.getTotalElements(),orders.getContent());
    }

    @ResponseBody
    @PostMapping("/update_state")
    public AjaxResult updateState(Long orderSid,Integer state){
        Order order = orderService.findOne(orderSid);
        order.setState(state);
        orderService.save(order);
        return AjaxResult.success();
    }

    @ResponseBody
    @PostMapping("/user_order_list")
    public AjaxResult userOrderList(int page,int limit,Long userSid){
        Page<Order> orders = orderService.orderList(page,limit,userSid);
        return AjaxResult.success(orders.getTotalElements(),orders.getContent());
    }

}
