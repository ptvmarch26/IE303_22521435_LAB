package com.example.demo.controller;

import com.example.demo.model.Shoe;
import com.example.demo.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shoes")
public class ShoeController {
    @Autowired
    private ShoeService shoeService;

    @GetMapping
    public List<Shoe> getAllShoes() {
        return shoeService.getAllShoes();
    }

    @GetMapping("/{shoeId}")
    public Shoe getDetailsShoe(@PathVariable String shoeId) {
        return shoeService.getDetailsShoe(shoeId);
    }
}
