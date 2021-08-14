package com.thomas.reciept.repository;

import java.util.ArrayList;

import com.thomas.reciept.model.Item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item,Integer> {

    ArrayList<Item> findByType(String name);
}
