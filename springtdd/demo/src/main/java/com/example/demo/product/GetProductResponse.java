package com.example.demo.product;

import org.springframework.util.Assert;

record GetProductResponse(long id, String name, int price, DiscountPolicy discountPolicy) {

    GetProductResponse {
        Assert.notNull(id, "상품 ID는 필수입니다");
        Assert.hasText(name, "상품명은 필수입니다");
        Assert.notNull(discountPolicy, "할인정책은 필수 입니다. ");
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.discountPolicy = discountPolicy;

    }
}
