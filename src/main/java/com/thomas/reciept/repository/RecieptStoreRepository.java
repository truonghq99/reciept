package com.thomas.reciept.repository;

import com.thomas.reciept.model.RecieptStore;
import com.thomas.reciept.model.Store;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecieptStoreRepository extends MongoRepository<RecieptStore, String>{

}
