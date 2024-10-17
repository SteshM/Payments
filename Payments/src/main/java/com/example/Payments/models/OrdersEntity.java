package com.example.Payments.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private long orderNo;
    private long quantity;
    private Double totalPrice;

    private Date orderDate = new Date();

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date() ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified = new Date();
    private String modifiedBy;
}

