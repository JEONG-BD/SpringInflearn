package com.example.demo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {
//    private Map<Long, Product> persistence = new HashMap<>();
//    private Long sequence = 0L;
//
//    public void save(final Product product) {
//        product.assigiId(++sequence);
//        persistence.put(product.getId(), product);
//    }
}
