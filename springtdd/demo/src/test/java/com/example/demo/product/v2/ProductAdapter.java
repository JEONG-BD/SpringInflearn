package com.example.demo.product.v2;

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
