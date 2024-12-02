package com.example.demo.product;

import com.example.demo.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class ProductApiTest  {


    @Test
    public void registerProduct () {
        //given
        final var request = ProductSteps.setAddProductRequest_Create();
        final var response = ProductSteps.setAddProductRequest(request);
        AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

}
