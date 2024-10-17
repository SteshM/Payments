package com.example.Payments.repository;

import com.example.Payments.enums.AvailabilityStatus;
import com.example.Payments.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByAvailabilityStatus(AvailabilityStatus availabilityStatus);

    ProductEntity findByProductId(long productId);
}

