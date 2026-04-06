package com.example.books.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.books.entities.Review;
import com.example.books.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @GetMapping
    public List<Review> listar() {
        return service.listar();
    }

    @PostMapping
    public Review criar(@RequestBody Review review) {
        return service.salvar(review);
    }
}