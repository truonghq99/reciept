package com.thomas.reciept.repository;

import com.thomas.reciept.model.Clothes;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesRepository extends MongoRepository<Clothes, Integer> {
    
}
