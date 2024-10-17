package com.example.Payments.dto.ResponseDTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductResDTO {
    private long productId;
    private String productName;
    private double price;
    private long stockQuantity;
    private  String category;

}
