package com.example.demo.product;

import com.example.demo.product.Product;
import jakarta.transaction.Transactional;
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
    private final ProductPort productPort;

    ProductService(final ProductPort productPort) {
        //this.productServicesTest = productServicesTest;
        this.productPort = productPort;
    }

    public void addProductOrigin(AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> addProductV1(@RequestBody final AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
