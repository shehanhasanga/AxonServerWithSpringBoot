package com.example.productService.command;

import com.example.productService.core.data.ProductLookupEntity;
import com.example.productService.core.data.ProductLookupRepository;
import com.example.productService.core.data.ProductRepository;
import com.example.productService.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler  {
    private final ProductLookupRepository productLookupRepository;

    ProductLookupEventHandler(ProductLookupRepository productLookupRepository){
        this.productLookupRepository = productLookupRepository;
    }
    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductLookupEntity productLookupEntity = new
                ProductLookupEntity();
        productLookupEntity.setProductId(event.getProductId());
        productLookupEntity.setTitle(event.getTitle());
        productLookupRepository.save(productLookupEntity);

    }
}
