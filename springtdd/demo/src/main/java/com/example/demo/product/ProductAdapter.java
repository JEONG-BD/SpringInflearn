package com.example.demo.product;

import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
class ProductAdapter implements ProductPart {

    //private final ProductServicesTest productServicesTest;
    private final ProductRepository productRepository;

    ProductAdapter(final ProductRepository productRepository) {
        //this.productServicesTest = productServicesTest;
        this.productRepository = new ProductRepository();
    }


    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
