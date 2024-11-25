package com.example.demo.product;

import com.example.demo.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/products")
class ProductService {

    //private final ProductServicesTest productServicesTest;
    private final ProductPart productPart;

    ProductService(final ProductPart productPart) {
        //this.productServicesTest = productServicesTest;
        this.productPart = productPart;
    }

    public void addProductOrigin(AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPart.save(product);
    }

    @PostMapping
    public ResponseEntity<Void> addProductV1(@RequestBody final AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPart.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
