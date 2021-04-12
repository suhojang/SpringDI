package com.jsh.di.controller;

import com.jsh.di.repository.SpringDIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringDIFieldController {
    @Autowired
    private SpringDIRepository repository;

    @GetMapping(value="/helloField")
    public String helloFiled(){
        return repository.getHello("Field");
    }
}
