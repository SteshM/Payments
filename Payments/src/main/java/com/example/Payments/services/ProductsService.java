package com.example.Payments.services;


import com.example.Payments.dto.RequestDTO.ProductDTO;
import com.example.Payments.dto.ResponseDTO.ProductResDTO;
import com.example.Payments.dto.ResponseDTO.ResponseDTO;
import com.example.Payments.enums.AvailabilityStatus;
import com.example.Payments.models.ProductEntity;
import com.example.Payments.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsService {
    private final DataService dataService;
    private final Utilities utilities;
    ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO addProduct(ProductDTO productDTO) throws JsonProcessingException {
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        productEntity.setAvailabilityStatus(AvailabilityStatus.IN_STOCK);
        log.info("About to save a product in the db {}",new ObjectMapper().writeValueAsString(productEntity));
        dataService.saveProduct(productEntity);
        return utilities.successResponse("Added a product",null);

    }

    public ResponseDTO viewProduct(long id){
        ProductEntity product = dataService.findProductById(id);
        var productResDTO = modelMapper.map(product, ProductResDTO.class);
        return utilities.successResponse("Successfully fetched a product",productResDTO);

    }
    //pagination
    //validation
    public ResponseDTO viewProducts() {
        List<ProductEntity> productEntityList = dataService.fetchProducts();
        List<ProductResDTO>productResDTOS = productEntityList.stream()
                .map(productEntity -> {
                    return ProductResDTO.builder()
                            .productId(productEntity.getProductId())
                            .productName(productEntity.getProductName())
                            .price(productEntity.getPrice())
                            .stockQuantity(productEntity.getStockQuantity())
                            .category(productEntity.getCategory())
                            .build();
                })
                .toList();
        return utilities.successResponse("Fetched all products from the db successfully",productResDTOS);

    }

    public ResponseDTO fetchProducts() {
        List<ProductEntity> products = dataService.FetchProductsInStock();
        List<ProductResDTO>productResDTOS = products.stream()
                .map(productEntity -> {
                    return ProductResDTO.builder()
                            .productId(productEntity.getProductId())
                            .productName(productEntity.getProductName())
                            .price(productEntity.getPrice())
                            .stockQuantity(productEntity.getStockQuantity())
                            .category(productEntity.getCategory())
                            .build();
                })
                .toList();
        return utilities.successResponse("Fetched all products in - stock from the db successfully",productResDTOS);

    }


    public ResponseDTO editProduct(long id, ProductDTO productDTO) {
        ProductEntity product = dataService.findProductById(id);
        product.setPrice(productDTO.getPrice());
        product.setProductName(productDTO.getProductName());
        product.setCategory(productDTO.getCategory());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setAvailabilityStatus(AvailabilityStatus.IN_STOCK);
        var updatedProduct = dataService.saveProduct(product);
        var productResDTO = modelMapper.map(updatedProduct, ProductResDTO.class);
        return utilities.successResponse("updated a product successfully",productResDTO);



    }

    public ResponseDTO deleteProduct(long id) {
        ProductEntity product = dataService.findProductById(id);
        product.setAvailabilityStatus(AvailabilityStatus.OUT_OF_STOCK);
        dataService.saveProduct(product);
        return utilities.successResponse("Deleted a product",null);

    }
    public ResponseDTO deletedProducts(){
        List<ProductEntity>products = dataService.fetchDeletedProducts();
        List<ProductResDTO>productResDTOList = products.stream().map(
                productEntity -> {
                    return ProductResDTO.builder()
                            .category(productEntity.getCategory())
                            .productId(productEntity.getProductId())
                            .price(productEntity.getPrice())
                            .stockQuantity(productEntity.getStockQuantity())
                            .productName(productEntity.getProductName())
                            .build();
                }
        ).toList();
        return utilities.successResponse("Fetched out-of-stock products",productResDTOList);

    }
}
