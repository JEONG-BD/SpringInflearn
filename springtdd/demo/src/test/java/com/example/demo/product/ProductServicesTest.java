package com.example.demo.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductServicesTest {

    private ProductService productService;
    private ProductPart productPart;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp(){
        productRepository = new ProductRepository();
        productPart  = new ProductAdapter(productRepository);
        productService = new ProductService(productPart);
    }

    @Test
    public void registerProduct () {
        //given
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.None;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);

        //when
        productService.addProduct(request);

        //then

    }
}
