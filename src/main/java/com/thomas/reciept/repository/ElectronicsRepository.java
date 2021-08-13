package com.thomas.reciept.repository;

import com.thomas.reciept.model.Electronics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicsRepository extends MongoRepository<Electronics, Integer> {
}
