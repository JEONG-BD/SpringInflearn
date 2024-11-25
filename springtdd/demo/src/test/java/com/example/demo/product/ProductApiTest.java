package com.example.demo.product;

import com.example.demo.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
public class ProductApiTest extends ApiTest {

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
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        //productService.addProduct(request);
    }

    private static AddProductRequest setAddProductRequest() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.None;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        return request;
    }
}
