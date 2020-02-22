package com.rx.demo.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.rx.demo.constant.CONSTANT;
import com.rx.demo.controller.BaseController;
import com.rx.demo.domain.User;
import com.rx.demo.service.RoleService;
import com.rx.demo.service.UserService;
import com.rx.demo.utils.AcsUtils;
import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.ShiroUtils;
import com.rx.demo.utils.Utils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class AppRootController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody String requestBody){
        JSONObject jsonObject = Utils.getRequestBody(requestBody);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            ShiroUtils.getCurrentSubject().login(token);
            return AjaxResult.success(ShiroUtils.getSession().getId());
        } catch (IncorrectCredentialsException e) {
            return AjaxResult.error("密码错误");
        } catch (UnknownAccountException e) {
            return AjaxResult.error("该用户不存在");
        } catch (LockedAccountException e) {
            return AjaxResult.error("该用户被锁定，请与管理员联系！");
        }
    }

    @PostMapping("/send_code")
    public AjaxResult sendCode(@RequestBody String requestBody){

        JSONObject jsonObject = Utils.getRequestBody(requestBody);
        String phone = jsonObject.getString("phone");

        String code = Utils.getCode();
        ShiroUtils.getSession().setAttribute(CONSTANT.CODE_KEY,code);
        AcsUtils.sendCode(phone,code);

       return AjaxResult.success("验证码发送成功，请注意查收");
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody String requestBody){
        JSONObject jsonObject = Utils.getRequestBody(requestBody);

//        String rcode = (String) ShiroUtils.getSession().getAttribute(CONSTANT.CODE_KEY);
//        if (Utils.isEmpty(rcode)){
//            return AjaxResult.error("请先获取验证码");
//        }
//
//        String code = jsonObject.getString("code");
//        if (!rcode.equals(code)){
//            if (Utils.isEmpty(rcode)){
//                return AjaxResult.error("验证码错误");
//            }
//        }

        Long invitarion = jsonObject.getLong("invitarion");

        if (Utils.isNull(userService.findOne(invitarion))){
            return AjaxResult.error("邀请码错误");
        }

        String username = jsonObject.getString("username");
        String phone = jsonObject.getString("phone");
        String password = Utils.getMd5(jsonObject.getString("password"),username);

        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);
        user.setCreateSid(invitarion);
        user.setRoleList(roleService.userRegister());
        userService.save(user);
        ShiroUtils.getSession().removeAttribute(CONSTANT.CODE_KEY);

        return AjaxResult.success();
    }

}
