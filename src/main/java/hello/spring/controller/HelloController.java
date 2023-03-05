package hello.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello-spring")
    public String hello() {
        System.out.println("HelloController.hello");
        return "Hello, Spring!";
    }
}
