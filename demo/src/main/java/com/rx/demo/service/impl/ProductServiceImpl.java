package com.rx.demo.service.impl;

import com.rx.demo.constant.STATUS;
import com.rx.demo.domain.Product;
import com.rx.demo.repositpry.ProductRepository;
import com.rx.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductRepository> implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    @Override
    public void setRepository(ProductRepository repository) {
        this.productRepository = repository;
        this.baseRepository = repository;
    }

    @Override
    public Page<Product> productList(int page, int limit) {
        return productRepository.findAll(PageRequest.of(page -1,limit));
    }

    @Override
    public List<Product> openProduct() {
        return productRepository.findAllByStatus(STATUS.PRODUCT_STATUS.OPEN.getStatus());
    }
}
