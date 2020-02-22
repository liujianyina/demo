package com.rx.demo.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.rx.demo.controller.BaseController;
import com.rx.demo.service.ProductService;
import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/product")
public class AppProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product_list")
    public AjaxResult productList(){
        return AjaxResult.success(productService.openProduct());
    }

    @ResponseBody
    @PostMapping("/product_info")
    public AjaxResult productInfo(@RequestBody String requestBody){
        JSONObject jsonObject = Utils.getRequestBody(requestBody);
        Long sid = jsonObject.getLong("sid");
        return AjaxResult.success(productService.findOne(sid));
    }

}
