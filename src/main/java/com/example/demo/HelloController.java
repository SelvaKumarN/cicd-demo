package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
        return "<h1>Hi World</h1>";
    }

    private void sample() {
        CompletableFuture.supplyAsync(() -> hello1()).join();
    }

    private String hello1() {
        return "hello";
    }

}