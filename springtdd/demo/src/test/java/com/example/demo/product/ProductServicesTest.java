package com.example.demo.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

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
    /*
    private static class AddProductRequest {

        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public AddProductRequest(final String name, final int price, final DiscountPolicy discountPolicy){
            Assert.hasText(name, "상품명은 필수 입니다");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다");

            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }


    }*/

    private record AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {

        private AddProductRequest{
            Assert.hasText(name, "상품명은 필수 입니다");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다");
        }

    }


    private enum DiscountPolicy{
        None
    }

    private class ProductService {

        private final ProductPart productPart;

        private ProductService(final ProductPart productPart) {
            this.productPart = productPart;
        }

        public void addProduct(AddProductRequest request) {
            final Product product = new Product(request.name, request.price, request.discountPolicy);
            productPart.save(product);
        }
    }

    private class Product {
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;
        private Long id;

        public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
            Assert.hasText(name, "상품명은 필수 입니다");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다");

            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }

        public void assigiId(final Long id) {
            this.id = id;
        }

        public Long getId() {
             return id;
        }
    }

    private interface ProductPart {

        public void save(final Product product);
    }

    private class ProductAdapter implements ProductPart {
        private final ProductRepository productRepository;

        private ProductAdapter(final ProductRepository productRepository) {
            this.productRepository = new ProductRepository();
        }


        @Override
        public void save(Product product) {
            productRepository.save(product);
        }
    }

    private class ProductRepository {
        private Map<Long, Product> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(final Product product) {
            product.assigiId(++sequence);
            persistence.put(product.getId(), product);
        }
    }
}
