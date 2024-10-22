package com.example.Payments.controller;

import com.example.Payments.dto.RequestDTO.ProductOrderDTO;
import com.example.Payments.dto.ResponseDTO.ResponseDTO;
import com.example.Payments.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments/order")
public class OrdersController {
    private final OrderService orderService;

    @PostMapping("/place-order")
    public ResponseDTO placeOrder(@RequestBody ProductOrderDTO productOrderDTO){
        return orderService.placeOrder(productOrderDTO);
    }

    @GetMapping("/orders")
    public ResponseDTO getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/product/{id}/order")
    public ResponseDTO getOderByProductId(@PathVariable long id){
        return orderService.getOrdersByProduct(id);
    }


}