package com.example.Payments.services;


import com.example.Payments.dto.RequestDTO.ProductOrderDTO;
import com.example.Payments.dto.ResponseDTO.ResponseDTO;
import com.example.Payments.models.OrdersEntity;
import com.example.Payments.models.ProductEntity;
import com.example.Payments.models.ProductOrderEntity;
import com.example.Payments.utils.AuthenticatedUser;
import com.example.Payments.utils.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final Utilities utilities;
    private final DataService dataService;
    private final AuthenticatedUser authenticatedUser;

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
        dataService.saveOrder(orders);


        return utilities.successResponse("success",null);
    }
}
