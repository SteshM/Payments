package com.example.Payments.services;


import com.example.Payments.dto.RequestDTO.ProductOrderDTO;
import com.example.Payments.dto.ResponseDTO.OrderResDto;
import com.example.Payments.dto.ResponseDTO.ResponseDTO;
import com.example.Payments.models.OrdersEntity;
import com.example.Payments.models.ProductEntity;
import com.example.Payments.models.ProductOrderEntity;
import com.example.Payments.utils.AuthenticatedUser;
import com.example.Payments.utils.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final Utilities utilities;
    private final DataService dataService;
    private final AuthenticatedUser authenticatedUser;
    ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO placeOrder(ProductOrderDTO productOrderDTO) {
        OrdersEntity orders = new OrdersEntity();
        orders.setOrderNo(productOrderDTO.getOrderNo());
        orders.setQuantity(productOrderDTO.getQuantity());

        log.info("Authenticated user {}", SecurityContextHolder.getContext().getAuthentication().getName());
        if (authenticatedUser == null){
            return utilities.failedResponse(01,"This user doesn't exist",null);
        }

        ProductEntity product = dataService.findProductById(productOrderDTO.getProductId());
        log.info("product {}",product.getStockQuantity());
        if (productOrderDTO.getQuantity()>(product.getStockQuantity()) ) {

            return utilities.failedResponse(01,"Insufficient stock for product"+ product.getProductName(),null);
        }
        Double totalPrice = productOrderDTO.getQuantity()*product.getPrice();
        product.setStockQuantity(product.getStockQuantity()-productOrderDTO.getQuantity());
        dataService.saveProduct(product);

        ProductOrderEntity productOrder = new ProductOrderEntity();
        productOrder.setOrder(orders);
        productOrder.setProduct(product);
        productOrder.setTotalPrice(totalPrice);
         var savedOrder = dataService.saveOrder(orders);
         var orderResDto = modelMapper.map(savedOrder, OrderResDto.class);
         orderResDto.setProductName(product.getProductName());
         orderResDto.setTotalPrice(totalPrice);


        return utilities.successResponse("success",orderResDto);
    }



    public ResponseDTO getAllOrders() {
        // Retrieve all orders
        List<OrdersEntity> ordersList = dataService.findAllOrders();

        if (ordersList.isEmpty()) {
            return utilities.failedResponse(01, "No orders found", null);
        }

        // Map orders to a DTO
        List<OrderResDto> orderResDtoList = ordersList.stream()
                .map(order -> {
            OrderResDto orderResDto = modelMapper.map(order, OrderResDto.class);

            List<ProductOrderEntity> productOrders = dataService.getProductOrders();
            if (productOrders != null && !productOrders.isEmpty()) {
                // Map first product details into the order response DTO (adjust logic as necessary)
                ProductEntity product = productOrders.get(0).getProduct();
                orderResDto.setProductName(product.getProductName());
                orderResDto.setTotalPrice(productOrders.get(0).getTotalPrice());
            }
            return orderResDto;
        }).collect(Collectors.toList());

        return utilities.successResponse("Orders retrieved successfully", orderResDtoList);
    }


    public ResponseDTO getOrdersByProduct(Long productId) {
        // Retrieve the product by its ID
        ProductEntity product = dataService.findProductById(productId);

        if (product == null) {
            return utilities.failedResponse(01, "Product not found", null);
        }

        // Retrieve orders associated with this product
        List<ProductOrderEntity> productOrders = dataService.findOrdersByProduct(product);

//        if (productOrders.isEmpty()) {
//            return utilities.failedResponse(01, "No orders found for product " + product.getProductName(), null);
//        }

        // Map the product orders to DTOs
        List<OrderResDto> orderResDtoList = productOrders.stream().map(productOrder -> {
            OrderResDto orderResDto = modelMapper.map(productOrder.getOrder(), OrderResDto.class);
            orderResDto.setProductName(product.getProductName());
            orderResDto.setTotalPrice(productOrder.getTotalPrice());
            return orderResDto;
        }).collect(Collectors.toList());

        return utilities.successResponse("Orders for product retrieved successfully", orderResDtoList);
    }



}
