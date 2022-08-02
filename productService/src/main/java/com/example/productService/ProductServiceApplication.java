package com.example.productService;

import com.example.productService.command.CreateProductCommandInteceptor;
import com.example.productService.core.errorhandling.ProdcutServiceEventsErrorHandler;
import javafx.application.Application;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus){
		CreateProductCommandInteceptor createProductCommandInteceptor = context.getBean(CreateProductCommandInteceptor.class);
		commandBus.registerDispatchInterceptor(createProductCommandInteceptor);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer){
		configurer.registerListenerInvocationErrorHandler("product-group", conf -> new ProdcutServiceEventsErrorHandler());

	}
}
