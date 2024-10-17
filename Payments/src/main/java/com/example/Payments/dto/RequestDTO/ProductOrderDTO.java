package com.example.Payments.dto.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductOrderDTO {
    private long productId;
    private long quantity;
    private long orderNo;
}
