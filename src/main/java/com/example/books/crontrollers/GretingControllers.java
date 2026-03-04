package com.example.books.crontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GretingControllers {
    private static final String template = "Hello. %s ";
            private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greetings")
    public Greeting greeting(@RequestParam(defaultValue = " Ola Alunos")){
        return new Greetings(counter.incrementAndGet(),template.formatted());
    }

    class Greeting()
}


