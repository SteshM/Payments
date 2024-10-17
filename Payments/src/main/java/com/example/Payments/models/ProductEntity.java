package com.example.Payments.models;


import com.example.Payments.enums.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String productName;
    private double price;
    private long stockQuantity;
    private  String category;
    private AvailabilityStatus availabilityStatus;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date() ;
    private String createdBy;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified = new Date();
    private String modifiedBy;
}

