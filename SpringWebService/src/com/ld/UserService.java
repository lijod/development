package com.ld;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ld.model.User;

@RestController
public class UserService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public User greeting(@RequestParam(value="username", defaultValue="World") String name) {
    	System.out.println("in greeting," + name);
        return new User(counter.incrementAndGet(), String.format(template, name));
    }
}