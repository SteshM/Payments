package com.example.Payments.controller;

import com.example.Payments.dto.RequestDTO.ProductOrderDTO;
import com.example.Payments.dto.ResponseDTO.ResponseDTO;
import com.example.Payments.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments/order")
public class OrdersController {
    private final OrderService orderService;

    @PostMapping("/place-order")
    public ResponseDTO placeOrder(@RequestBody ProductOrderDTO productOrderDTO){
        return orderService.placeOrder(productOrderDTO);
    }


}