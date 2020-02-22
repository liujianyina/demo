package com.rx.demo.controller;

import com.rx.demo.domain.Properties;
import com.rx.demo.service.PropertiesService;
import com.rx.demo.utils.AcsUtils;
import com.rx.demo.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController extends BaseController {

    @Autowired
    private PropertiesService propertiesService;

    @GetMapping("/properties")
    public String properties(Model model) {
        Map<String, String> map = new HashMap<>();
        propertiesService.findAll().forEach(p -> map.put(p.getKeey(), p.getValue()));
        model.addAllAttributes(map);
        return "/admin/properties";
    }

    @ResponseBody
    @PostMapping("/acs_test")
    public AjaxResult acsTest(String accessKeyId, String accessSecret, String templateCode, String signName, String phoneNumbers) {
        return AjaxResult.success(AcsUtils.test(accessKeyId, accessSecret, templateCode, signName, phoneNumbers));
    }

    @ResponseBody
    @PostMapping("/acs_update")
    public AjaxResult acsUpdate(@RequestParam Map<String,String> map){

        List<Properties> ps = propertiesService.findAll();
        Map<String, Properties> m = new HashMap<>();
        ps.forEach(p -> m.put(p.getKeey(), p));

        map.forEach((k,v)->{
            Properties p = m.get(k);
            p.setValue(v);
            propertiesService.save(p);
        });

        AcsUtils.init();

        return AjaxResult.success();
    }


    @ResponseBody
    @PostMapping("/update_customer_service_url")
    public AjaxResult updateCustomerServiceUrl(@RequestParam Map<String,String> map){

        List<Properties> ps = propertiesService.findAll();
        Map<String, Properties> m = new HashMap<>();
        ps.forEach(p -> m.put(p.getKeey(), p));

        map.forEach((k,v)->{
            System.out.println( k + "  " + v);
            Properties p = m.get(k);
            p.setValue(v);
            propertiesService.save(p);
        });

        return AjaxResult.success();
    }
}
