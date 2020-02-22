package com.rx.demo.controller.app;

import com.rx.demo.controller.BaseController;
import com.rx.demo.service.PropertiesService;
import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/user")
public class AppUserController extends BaseController {

    @Autowired
    private PropertiesService propertiesService;

    @PostMapping("/get_user_info")
    public AjaxResult getUserInfo() {
        return AjaxResult.success(ShiroUtils.getCurrentUser());
    }

    @PostMapping("/customer_service_url")
    public AjaxResult customerServiceUrl() {
        Map<String, String> map = new HashMap<>();
        propertiesService.findAll().forEach(p -> map.put(p.getKeey(), p.getValue()));
        return AjaxResult.success(map);
    }

}
