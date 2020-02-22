package com.rx.demo.controller;

import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.ShiroUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController extends BaseController {

    @GetMapping({"/login", "/"})
    public String login() {
        if (ShiroUtils.getCurrentSubject().isAuthenticated()) {
            return "redirect:/product/product_list";
        } else {
            return "/login";
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public AjaxResult login(String username, String password) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            ShiroUtils.getCurrentSubject().login(token);
            return AjaxResult.success();
        } catch (IncorrectCredentialsException e) {
            return AjaxResult.error("密码错误");
        } catch (UnknownAccountException e) {
            return AjaxResult.error("该用户不存在");
        } catch (LockedAccountException e) {
            return AjaxResult.error("该用户被锁定，请与管理员联系！");
        }
    }


}
