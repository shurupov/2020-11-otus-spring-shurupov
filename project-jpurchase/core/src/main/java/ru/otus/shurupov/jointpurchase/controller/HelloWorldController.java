package ru.otus.shurupov.jointpurchase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping( "/hello")
    public String firstPage() {
        return "Hello World";
    }
}

