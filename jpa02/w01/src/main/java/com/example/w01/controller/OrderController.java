package com.example.w01.controller;

import com.example.w01.domain.Member;
import com.example.w01.domain.Order;
import com.example.w01.domain.item.Item;
import com.example.w01.repository.OrderSearch;
import com.example.w01.service.ItemService;
import com.example.w01.service.MemberService;
import com.example.w01.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createOrderForm(Model model){
        List<Member> members = memberService.all();
        List<Item> items = itemService.findAll();
        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId")  Long itemId,
                              @RequestParam("count") int count){

        orderService.createOrder(memberId, itemId, count);

        return "redirect:/order";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.orderSearch(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String orderCancel(@PathVariable("orderId") Long orderId){
        orderService.orderCancel(orderId);
        return "redirect:/orders";
    }

}
