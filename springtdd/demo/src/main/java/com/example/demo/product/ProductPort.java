package com.example.demo.product;

import com.example.demo.product.Product;

public interface ProductPort {

    void save(final Product product);

    Product getProduct(long productId);
}
