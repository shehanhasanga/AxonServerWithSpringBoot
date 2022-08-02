package com.example.productService.command;

import com.example.productService.core.data.ProductLookupEntity;
import com.example.productService.core.data.ProductLookupRepository;
import org.aspectj.bridge.ICommand;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInteceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandInteceptor.class);
    private final ProductLookupRepository productLookupRepository;
    CreateProductCommandInteceptor(ProductLookupRepository productLookupRepository){
        this.productLookupRepository = productLookupRepository;
    }
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {

        return (index, command ) -> {
            logger.info("Intercepted Command : " + command.getPayloadType());
            if(CreateProductCommand.class.equals(command.getPayloadType())){
                CreateProductCommand createProductCommand = (CreateProductCommand)command.getPayload();
                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(),createProductCommand.getTitle());
                if(productLookupEntity != null){
                    throw new IllegalStateException(String.format("Product with product id %s or title %s already exists", createProductCommand.getProductId(), createProductCommand.getTitle()));
                    
                }
            }
            return command;
        };
    }
}
