package com.thomas.reciept.repository;

import com.thomas.reciept.model.Reciept;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecieptRepository extends MongoRepository<Reciept, String> {
}
