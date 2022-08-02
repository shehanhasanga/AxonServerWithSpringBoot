package com.example.productService.command;

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
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {

        return (index, command ) -> {
            logger.info("Intercepted Command : " + command.getPayloadType());
            if(CreateProductCommand.class.equals(command.getPayloadType())){
                CreateProductCommand createProductCommand = (CreateProductCommand)command.getPayload();
                if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0){
                    throw new IllegalArgumentException("Price can not be equal or less than Zero");
                }
                if(createProductCommand.getTitle() == null || createProductCommand.getTitle().isEmpty()){
                    throw new IllegalArgumentException("Title can not be empty");
                }
            }
            return command;
        };
    }
}
