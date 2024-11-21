package com.example.demo.product;

import com.example.demo.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
class ProductRepository {
    private Map<Long, Product> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(final Product product) {
        product.assigiId(++sequence);
        persistence.put(product.getId(), product);
    }
}
