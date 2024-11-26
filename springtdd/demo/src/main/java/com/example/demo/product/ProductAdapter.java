package com.example.demo.product;

import org.springframework.stereotype.Component;

@Component
class ProductAdapter implements ProductPort {

    //private final ProductServicesTest productServicesTest;
    private ProductRepository productRepository;

    ProductAdapter(final ProductRepository productRepository) {
        //this.productServicesTest = productServicesTest;
        this.productRepository = productRepository;
    }


    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
