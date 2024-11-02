package com.example.product.controller;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.dto.SaleRequest;
import com.example.product.service.CustomerService;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class SaleController {

    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public SaleController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @PostMapping("/addCustomer")
    ResponseEntity<?> addCustomer(@RequestBody SaleRequest saleRequest){
        return new ResponseEntity<>(customerService.AddCustomer(saleRequest), HttpStatus.CREATED);
    }

    @GetMapping("/addProduct")
    ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(addProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("/viewProduct")
    public ResponseEntity<ProductResponse> getAllProduct(){
        ProductResponse productResponse=ProductResponse.builder()
                .message("Get All Product")
                .payload(productService.getAllProducts())
                .httpStatus(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?>getById(@PathVariable int id){
        if(productService.getProductById(id).isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.valueOf(404));
        }else {
            ProductResponse productResponse = ProductResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .payload(productService.getProductById(id))
                    .message("Search Product")
                    .build();
            return new ResponseEntity<>(productResponse,HttpStatus.OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductRequest productRequest){
        if(productService.updateProductById(id, productRequest).isEmpty()){
            ProductResponse productResponse=ProductResponse.builder()
                    .message("Failed to update product")
                    .payload(null)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(productResponse,HttpStatus.NOT_FOUND);

        }else {
            ProductResponse productResponse=ProductResponse.builder()
                    .message("Successfully updated product")
                    .payload(productService.updateProductById(id,productRequest))
                    .httpStatus(HttpStatus.OK)
                    .build();
            return new ResponseEntity<>(productResponse,HttpStatus.OK);
        }

    }


}
