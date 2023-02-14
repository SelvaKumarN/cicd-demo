package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    void should_return_hello_world() {
        HelloController helloController = new HelloController();
        Assertions.assertTrue(helloController.hello().contains("Hi World"));
    }
}
