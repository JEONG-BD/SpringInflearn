package com.example.demo.product;

import com.example.demo.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class ProductSteps {
    public static ExtractableResponse<Response> setAddProductRequest(final AddProductRequest request) {

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();

        //productService.addProduct(request);
    }

    public static AddProductRequest setAddProductRequest_Create() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.None;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        return request;
    }
}
