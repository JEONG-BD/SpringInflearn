package com.example.demo.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ProductServicesTest {

    @Autowired
    private ProductService productService;
//    private ProductRepository productRepository;
//    private ProductPart productPart;

//    @BeforeEach
//    void setUp(){
//        productRepository = new ProductRepository();
//        productPart  = new ProductAdapter(productRepository);
//        productService = new ProductService(productPart);
//    }


    @Test
    public void registerProduct () {
        //given

        final AddProductRequest request = setAddProductRequest();

        productService.addProduct(request);
    }

    private static AddProductRequest setAddProductRequest() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.None;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        return request;
    }
}
