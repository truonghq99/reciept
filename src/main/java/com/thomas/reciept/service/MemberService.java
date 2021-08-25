package com.thomas.reciept.service;

import java.util.ArrayList;

import com.thomas.reciept.model.Member;
import com.thomas.reciept.repository.MemberRepository;
import com.thomas.reciept.security.CustomMemberDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService{
    
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        CustomMemberDetails userDetails =null;
        if(member!=null){
            userDetails = new CustomMemberDetails();
            userDetails.setMember(member);
        }else{
            throw new UsernameNotFoundException("Username does not exist with username: "+username);
        }
        return userDetails;
    }

    public boolean checkUsername(String username){
        Member member= memberRepository.findByUsername(username);
        if(member==null){
            return true;
        }else{
            return false;
        }
    }

    public Member saveMember(Member member){
        String passwordEncode = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(passwordEncode);
        return memberRepository.save(member);
    }

    public void updateMember(String id,Member member){
        Member mem= memberRepository.findMemberById(id);
        if(member.getId().equalsIgnoreCase("")){
            log.info("ID does not match");
        }else{
            mem.setId(member.getId());
                mem.setPassword(member.getPassword());
                mem.setFirstName(member.getFirstName());
                mem.setLastName(member.getLastName());
                mem.setDateOfBirth(member.getDateOfBirth());
                mem.setPhoneNumber(member.getPhoneNumber());
                mem.setEmail(member.getEmail());
                mem.setAddress(member.getAddress());
                mem.setDistrict(member.getDistrict());
                mem.setCity(member.getCity());
                mem.setState(member.getState());
                mem.setCountry(member.getCountry());
                mem.setPosition(member.getPosition());
                mem.setImage(member.getImage());
                memberRepository.save(mem);
        }
    }

    public ArrayList<Member> getAllMember(){
        return (ArrayList<Member>) memberRepository.findAll();
    }
    public void deleteMember(String id){
        if(memberRepository.existsById(id)){
            memberRepository.deleteById(id);
        }else{
            log.info("");
        }
    }

    
}
