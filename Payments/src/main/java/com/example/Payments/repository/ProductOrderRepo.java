package com.example.Payments.repository;


import com.example.Payments.models.ProductEntity;
import com.example.Payments.models.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepo  extends JpaRepository<ProductOrderEntity,Long> {
    List<ProductOrderEntity> findByProduct(ProductEntity product);
}
