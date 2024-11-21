package com.example.demo.product.v2;

import java.util.HashMap;
import java.util.Map;

class ProductRepository {
    private Map<Long, Product> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(final Product product) {
        product.assigiId(++sequence);
        persistence.put(product.getId(), product);
    }
}
