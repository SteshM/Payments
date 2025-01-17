package com.example.Payments.services;


import com.example.Payments.enums.AvailabilityStatus;
import com.example.Payments.models.OrdersEntity;
import com.example.Payments.models.ProductEntity;
import com.example.Payments.models.ProductOrderEntity;
import com.example.Payments.repository.OrderRepo;
import com.example.Payments.repository.ProductOrderRepo;
import com.example.Payments.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final ProductOrderRepo productOrderRepo;

    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepo.save(productEntity);
    }

    public List<ProductEntity> fetchProducts() {
        return productRepo.findAll();
    }

    public List <ProductEntity> FetchProductsInStock() {
        return productRepo.findByAvailabilityStatus(AvailabilityStatus.IN_STOCK);
    }

    public ProductEntity findProductById(long productId) {
        return productRepo.findByProductId(productId);
    }

    public List<ProductEntity> fetchDeletedProducts() {
        return productRepo.findByAvailabilityStatus(AvailabilityStatus.OUT_OF_STOCK);
    }

    public OrdersEntity saveOrder(OrdersEntity orders) {
       return orderRepo.save(orders);
    }

    public List<OrdersEntity> findAllOrders() {
        return orderRepo.findAll();
    }

    public List<ProductOrderEntity> getProductOrders() {
        return productOrderRepo.findAll();
    }

    public List<ProductOrderEntity> findOrdersByProduct(ProductEntity product) {
        return productOrderRepo.findByProduct(product);
    }
}
