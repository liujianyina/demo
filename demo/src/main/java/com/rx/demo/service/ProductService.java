package com.rx.demo.service;

import com.rx.demo.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    Page<Product> productList(int page,int limit);

    List<Product> openProduct();

}
