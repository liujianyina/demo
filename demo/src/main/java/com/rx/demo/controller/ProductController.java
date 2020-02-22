package com.rx.demo.controller;

import com.rx.demo.domain.Product;
import com.rx.demo.service.ProductService;
import com.rx.demo.task.DealKPoint;
import com.rx.demo.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;

    @GetMapping("/product_list")
    public String productList(){
        return "/product/product_list";
    }

    @ResponseBody
    @PostMapping("/product_list")
    public AjaxResult productList(int page,int limit){
        Page<Product> products = productService.productList(page,limit);
        return AjaxResult.success(products.getTotalElements(),products.getContent());
    }



    @GetMapping("/product_create")
    public String productCreate(){
        return "/product/product_create";
    }

    @ResponseBody
    @PostMapping("/product_create")
    public AjaxResult productCreate(Product product){
        productService.save(product);
        DealKPoint.dealValue(product.getMin(),product.getMax(),product.getSid());
        return AjaxResult.success();
    }

    @GetMapping("/product_kline")
    public String productKline(Long productSid, Model model){
        model.addAttribute("product",productService.findOne(productSid));
        return "/product/product_kline";
    }

    @ResponseBody
    @PostMapping("/update_status")
    public AjaxResult updateStatus(Long productSid,Integer status){
        Product product = productService.findOne(productSid);
        product.setStatus(status);
        productService.save(product);
        return AjaxResult.success();
    }

    @ResponseBody
    @PostMapping("/product_del")
    public AjaxResult productDel(Long productSid){
        productService.delete(productSid);
        return AjaxResult.success();
    }

}
