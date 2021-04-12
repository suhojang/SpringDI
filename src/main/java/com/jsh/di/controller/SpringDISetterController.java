package com.jsh.di.controller;

import com.jsh.di.repository.SpringDIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringDISetterController {
    private SpringDIRepository repository;

    @Autowired
    public void setRepository(SpringDIRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value="/helloSetter")
    public String helloSetter(){
        return repository.getHello("Setter");
    }
}
