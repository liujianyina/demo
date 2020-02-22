package com.rx.demo.repositpry;

import com.rx.demo.domain.Product;

import java.util.List;

public interface ProductRepository extends  BaseRepository<Product> {

    List<Product> findAllByStatus(Integer status);

}
