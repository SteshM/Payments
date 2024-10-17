package com.example.Payments.dto.RequestDTO;

import com.example.Payments.enums.AvailabilityStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
    private String productName;
    private double price;
    private long stockQuantity;
    private  String category;
    private AvailabilityStatus availabilityStatus;
}
