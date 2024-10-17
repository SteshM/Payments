package com.example.Payments.repository;

import com.example.Payments.models.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrdersEntity,Long> {
}
