package com.rx.demo.controller;

import com.rx.demo.constant.CONSTANT;
import com.rx.demo.constant.TYPE;
import com.rx.demo.domain.Role;
import com.rx.demo.domain.Turnover;
import com.rx.demo.domain.User;
import com.rx.demo.service.RoleService;
import com.rx.demo.service.TurnoverService;
import com.rx.demo.service.UserService;
import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.spi.SyncResolver;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TurnoverService turnoverService;

    @GetMapping("/user_list")
    public String userList(Model model){
        model.addAttribute("roles",roleService.findAll());
        return "/user/user_list";
    }

    @ResponseBody
    @PostMapping("/user_list")
    public AjaxResult userList(@RequestParam(name = "roleSids[]",required = false) List<Long> roleSids, int page, int limit){
        Page<User> userPage = userService.userList(page,limit,roleSids);
        return AjaxResult.success(userPage.getTotalElements(),userPage.getContent());
    }

    @GetMapping("/customer_list")
    public String customerList(Long userSid,Model model){
        model.addAttribute("userSid",userSid);
        return "/user/customer_list";
    }

    @ResponseBody
    @PostMapping("/customer_list")
    public AjaxResult customerList(int page,int limit,Long userSid){
        Page<User> users = userService.userList(page,limit,userSid);
        return AjaxResult.success(users.getTotalElements(),users.getContent());
    }

    @GetMapping("/order_list")
    public String  orderList(Long userSid,Model model){
        model.addAttribute("userSid",userSid);
        return "/user/order_list";
    }

    @GetMapping("/turnover_list")
    public String turnoverList(Long userSid,Model model){
        model.addAttribute("userSid",userSid);
        return "/user/turnover_list";
    }

    @ResponseBody
    @PostMapping("/user_add")
    public AjaxResult userAdd(Long userSid,Float money){

        User user = userService.findOne(userSid);
        Float m = user.getMoney() + money;
        user.setMoney(m);
        userService.save(user);

        Turnover turnover = new Turnover();
        turnover.setUserSid(userSid);
        turnover.setMoney(money);
        turnover.setBalance(m);
        turnover.setUsername(user.getUsername());
        turnover.setType(TYPE.TURNOVER_TYPE.ADD.getType());
        turnover.setText(String.format(CONSTANT.TURNOVER.TOP_UP,money));
        turnoverService.save(turnover);

        return AjaxResult.success();
    }

    @ResponseBody
    @PostMapping("/reset_pass")
    public AjaxResult resetPass(Long userSid,String password){
        User user = userService.findOne(userSid);
        user.setPassword(Utils.getMd5(password,user.getUsername()));
        userService.save(user);
        return AjaxResult.success();
    }

    @ResponseBody
    @PostMapping("/del")
    public AjaxResult del(Long userSid){
        userService.delete(userSid);
        return AjaxResult.success();
    }

    @GetMapping("/user_create")
    public String userCreate(Model model){
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles",roles);
        return "/user/user_create";
    }

    @ResponseBody
    @PostMapping("/user_create")
    public AjaxResult userCreate(String username,String phone,String password,@RequestParam(name = "roleSids[]") List<Long> roleSids){
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(Utils.getMd5(password,username));
        List<Role> roles = roleService.findBySids(roleSids);
        user.setRoleList(roles);
        userService.save(user);
        return AjaxResult.success();
    }
}
