package com.example.product.service;

import com.example.product.dto.ProductRequest;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private  ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product AddProduct(ProductRequest productRequest){
        System.out.println(productRequest.getProduct());
        return productRepository.save(productRequest.getProduct());
    }

    public  List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }

    public Optional<Product> updateProductById(int id, ProductRequest productRequest){
        Optional<Product> product= productRepository.findById(id);
        product.get().setProductName(productRequest.getProduct().getProductName());
        product.get().setPrice(productRequest.getProduct().getPrice());
       return productRepository.findById(id);
    }
}
