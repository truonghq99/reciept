package com.thomas.reciept.repository;

import com.thomas.reciept.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier,Integer> {
}
