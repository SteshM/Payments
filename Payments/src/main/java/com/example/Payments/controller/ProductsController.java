package com.example.Payments.controller;


import com.example.Payments.dto.RequestDTO.ProductDTO;
import com.example.Payments.dto.ResponseDTO.ResponseDTO;
import com.example.Payments.dto.ResponseDTO.UserProfileDTO;
import com.example.Payments.services.CacheService;
import com.example.Payments.services.ProductsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments/product")

public class ProductsController {
    private final ProductsService productsService;
    private final CacheService cacheService;

    @PostMapping("/add")
    public ResponseDTO addProduct(@RequestBody ProductDTO productDTO) throws JsonProcessingException {
        return productsService.addProduct(productDTO);
    }
    @GetMapping("/one/{id}")
    public ResponseDTO viewProduct(@PathVariable long id){
        return productsService.viewProduct(id);
    }
    @GetMapping("/all")
    public ResponseDTO viewProducts(){
        return productsService.viewProducts();
    }
    @GetMapping("/in-stock")
    public ResponseDTO fetchProducts(){
        return productsService.fetchProducts();
    }
    @PutMapping("/{id}")
    public ResponseDTO updateProduct(@PathVariable long id, @RequestBody ProductDTO productDTO){
        return productsService.editProduct(id,productDTO);
    }
    @DeleteMapping("/del/{id}")
    public ResponseDTO deleteProduct(@PathVariable long id){
        return productsService.deleteProduct(id);
    }

    @GetMapping("/deleted")
    public ResponseDTO deletedProducts(){
        return productsService.deletedProducts();
    }

    @GetMapping("/cache")
    public List<UserProfileDTO> getUsers(){
        return cacheService.getUsers();
    }



}
