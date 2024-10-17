package com.example.Payments.repository;


import com.example.Payments.models.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepo  extends JpaRepository<ProductOrderEntity,Long> {
}
