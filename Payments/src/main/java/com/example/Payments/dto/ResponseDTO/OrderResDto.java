package com.example.Payments.dto.ResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResDto {
    private String productName;
    private long quantity;
    private long orderNo;
    private double totalPrice;
}
