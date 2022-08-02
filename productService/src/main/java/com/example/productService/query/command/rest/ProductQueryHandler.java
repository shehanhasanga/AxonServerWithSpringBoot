package com.example.productService.query.command.rest;

import com.example.productService.core.data.ProductEntity;
import com.example.productService.core.data.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {
    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery findProductsQuery){
        List<ProductRestModel> models = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();
        for (ProductEntity productEntity : productEntities){
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity,productRestModel);
            models.add(productRestModel);
        }
        return models;
    }
}
