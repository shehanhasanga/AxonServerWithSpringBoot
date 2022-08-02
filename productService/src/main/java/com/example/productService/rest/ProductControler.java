package com.example.productService.rest;

import com.example.productService.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductControler {
    private Environment environment;
    private CommandGateway commandGateway;

    @Autowired
    ProductControler(Environment environment , CommandGateway commandGateway){
        this.commandGateway = commandGateway;
        this.environment = environment;
    }

    @PostMapping()
    public String createProduct( @Valid @RequestBody CreateProductRestModel createProductRestModel){
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quatity(createProductRestModel.getQuatity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();
        String returnId = "";
        returnId = commandGateway.sendAndWait(createProductCommand);

//        try {
//            returnId = commandGateway.sendAndWait(createProductCommand);
//
//        }catch (Exception e){
//            returnId = e.getLocalizedMessage();
//        }
        return "HTTP post is handled : " + returnId;

    }

//    @GetMapping
//    public String getProduct(){
//        return "HTTP get is handled: " + environment.getProperty("local.server.port");
//    }
//
//    @PutMapping
//    public String updateProduct(){
//        return "HTTP put is handled";
//    }
//
//    @DeleteMapping
//    public String deleteProduct(){
//        return "HTTP delete is handled";
//    }
}
