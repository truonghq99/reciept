package com.thomas.reciept.service;

import com.thomas.reciept.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;

    
}
