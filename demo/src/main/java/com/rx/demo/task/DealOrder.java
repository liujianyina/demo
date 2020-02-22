package com.rx.demo.task;

import com.rx.demo.constant.CONSTANT;
import com.rx.demo.constant.STATUS;
import com.rx.demo.constant.STATUS.ORDER_STATE;
import com.rx.demo.constant.TYPE;
import com.rx.demo.domain.Order;
import com.rx.demo.domain.Turnover;
import com.rx.demo.domain.User;
import com.rx.demo.service.OrderService;
import com.rx.demo.service.TurnoverService;
import com.rx.demo.service.UserService;
import com.rx.demo.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class DealOrder {

    private static OrderService orderService;
    private static UserService userService;
    private static TurnoverService turnoverService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        DealOrder.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        DealOrder.userService = userService;
    }

    @Autowired
    public void setTurnoverService(TurnoverService turnoverService) {
        DealOrder.turnoverService = turnoverService;
    }

    public static void deal(Long orderSid,Integer time) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                Order order = orderService.findOne(orderSid);

                Float money; //订单实际盈亏金额
                Float addMoney; //需要加回去的金额
                Turnover turnover = new Turnover();

                if (order.getState().equals(ORDER_STATE.LOSE.getState())) {  //亏损
                    /**
                     * 亏损
                     * 1、修改订单盈亏
                     * 2、生成流水
                     */
                    money = -order.getOrderMoney();
                    addMoney = 0F;
                    turnover.setText(String.format(CONSTANT.TURNOVER.BUY, order.getProductName(), order.getOrderMoney(), "亏损", order.getOrderMoney()));
                    turnover.setMoney(money);
                } else if (order.getState().equals(STATUS.ORDER_STATE.PING.getState())) {  //平仓
                    /**
                     * 平仓
                     * 1、修改订单盈亏
                     * 2、加回余额
                     * 3、生成流水
                     */
                    money = 0F;
                    addMoney = order.getOrderMoney();
                    turnover.setText(String.format(CONSTANT.TURNOVER.BUY, order.getProductName(), order.getOrderMoney(), "持平", addMoney * 0.4F));
                    turnover.setMoney(money);
                } else {  //盈利
                    /**
                     * 平仓
                     * 1、修改订单盈亏
                     * 2、加回余额
                     * 3、生成流水
                     */
                    money = order.getOrderMoney() * 0.4F;
                    addMoney = order.getOrderMoney() * 1.4F;
                    turnover.setText(String.format(CONSTANT.TURNOVER.BUY, order.getProductName(), order.getOrderMoney(), "盈利", 0));
                    turnover.setMoney(money);
                }

                order.setMoney(money);
                order.setSuccess(STATUS.SUCCESS.ALREADY_SUCCESS.getSuccess());
                orderService.save(order);

                User user = userService.findOne(order.getUserSid());
                user.setMoney(user.getMoney() + addMoney);
                User u = userService.save(user);
                ShiroUtils.refresh(u);

                turnover.setUserSid(u.getSid());
                turnover.setUsername(u.getUsername());
                turnover.setType(TYPE.TURNOVER_TYPE.BUY.getType());
                turnover.setBalance(u.getMoney());
                turnoverService.save(turnover);

            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, time * 1000);
    }


}
