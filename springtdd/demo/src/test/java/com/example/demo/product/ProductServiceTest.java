package com.example.demo.product;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {


    @Autowired
    private ProductService productService;


    @Test
    public void findProduct  (){
    	//given
        productService.addProductV1(ProductSteps.setAddProductRequest_Create());

        final long productId = 1L;

        final GetProductResponse response = productService.getProduct(productId);

        //when

        //then
        Assertions.assertThat(response).isNotNull();
    }

}
