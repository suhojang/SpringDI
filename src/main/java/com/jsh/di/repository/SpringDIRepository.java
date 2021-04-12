package com.jsh.di.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringDIRepository {
    private Logger logger   = LoggerFactory.getLogger(SpringDIRepository.class);

    public String getHello(String type){
        String message  = "hello Spring Dependency Injection " + type;
        logger.info(message);
        return message;
    }
}
