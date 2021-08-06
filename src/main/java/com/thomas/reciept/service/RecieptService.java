package com.thomas.reciept.service;

import com.thomas.reciept.model.Reciept;
import com.thomas.reciept.repository.RecieptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class RecieptService {

    @Autowired
    RecieptRepository recieptRepository;

    public Reciept saveReciept(Reciept reciept){
        log.info("saveRecipt in RecieptService");
        return recieptRepository.save(reciept);
    }

    public Optional<Reciept> findRecieptById(String id){
        return recieptRepository.findById(id);
    }

    public ArrayList<Reciept> getAllReciepts(){
        return (ArrayList<Reciept>) recieptRepository.findAll();
    }
}
