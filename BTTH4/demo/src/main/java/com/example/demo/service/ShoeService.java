package com.example.demo.service;

import com.example.demo.model.Shoe;
import com.example.demo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService {
    @Autowired
    private ShoeRepository shoeRepository;

    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    public Shoe getDetailsShoe(String shoeId) {
        return shoeRepository.findById(shoeId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
    }
}
