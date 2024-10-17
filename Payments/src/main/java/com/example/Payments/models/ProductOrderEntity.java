package com.example.Payments.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "product_order")
public class ProductOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productOrderId;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrdersEntity order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;
}
