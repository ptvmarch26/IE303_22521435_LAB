package com.example.demo.repository;

import com.example.demo.model.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends MongoRepository<Shoe, String> {
}
