package com.example.demo.product.v2;

class ProductService {

    //private final ProductServicesTest productServicesTest;
    private final ProductPart productPart;

    ProductService(final ProductPart productPart) {
        //this.productServicesTest = productServicesTest;
        this.productPart = productPart;
    }

    public void addProduct(AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPart.save(product);
    }
}
