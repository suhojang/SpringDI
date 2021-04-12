package com.jsh.di.controller;

import com.jsh.di.repository.SpringDIRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringDIConstructorController {
    private SpringDIRepository repository;

    public SpringDIConstructorController(SpringDIRepository repository){
        this.repository = repository;
    }

    @GetMapping(value="/helloConstructor")
    public String helloConstructor(){
        return repository.getHello("Constructor");
    }
}
