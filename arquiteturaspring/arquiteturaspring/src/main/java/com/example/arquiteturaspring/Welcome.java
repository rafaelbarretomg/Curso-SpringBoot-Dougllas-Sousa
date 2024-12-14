package com.example.arquiteturaspring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/")
public class Welcome {

    @GetMapping
    public String welcome() {
        return new String("BEM VINDO!");
    }
    
}
