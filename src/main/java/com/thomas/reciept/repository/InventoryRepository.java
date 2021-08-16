package com.thomas.reciept.repository;

import com.thomas.reciept.model.Inventory;
import com.thomas.reciept.model.Store;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository <Inventory, Integer>{
    
    @Query("{id:?0}")
    public Inventory findInventoryById(int id);
}
