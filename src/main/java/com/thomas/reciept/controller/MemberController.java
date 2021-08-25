package com.thomas.reciept.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.thomas.reciept.model.Member;
import com.thomas.reciept.service.FileUploadUtil;
import com.thomas.reciept.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/rest/api/member")
@Slf4j
public class MemberController{

    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/create-member")
    public String createMember(Member member, Model model){
        model.addAttribute("member", member);
        return "Create/create-member";

    }

    @PostMapping("/save-member")
    public String saveMember(
            @Valid @ModelAttribute("member")Member member,
            BindingResult bindingResult,
            @RequestParam("image")MultipartFile multipartFile) throws IOException {
        String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        member.setImage(fileName);
        Boolean check=memberService.checkUsername(member.getUsername());
        System.out.println(check);
        // if(bindingResult.hasErrors()){
        //     return "Create/create-member";
        // }
        if(check==true){
            memberService.saveMember(member);
            String uploadDir= "src/main/resources/static/image/user-photos/"+ member.getId();
            FileUploadUtil.saveFile(uploadDir, fileName,multipartFile);
        }else{
            log.info("username exist");
        }
        return "index";
    }

    @GetMapping("/members")
    public String listAllMember(Model model, Member member, HttpSession session){
        ArrayList<Member> list = memberService.getAllMember();
        model.addAttribute("listMember", list);
        session.setAttribute("listMember", list);
        return "List/list-member";
    }

    @GetMapping("/members/{id}")
    public String detailsMember(@PathVariable String id, HttpSession session, Model model){
        ArrayList<Member> list= (ArrayList<Member>) session.getAttribute("listMember");
        Member member= new Member();
        for(int i=0;i< list.size();i++){
            if(list.get(i).getId().equalsIgnoreCase(id)){
                member.setId(list.get(i).getId());
                member.setUsername(list.get(i).getUsername());
                member.setPassword(list.get(i).getPassword());
                member.setFirstName(list.get(i).getFirstName());
                member.setLastName(list.get(i).getLastName());
                member.setDateOfBirth(list.get(i).getDateOfBirth());
                member.setPhoneNumber(list.get(i).getPhoneNumber());
                member.setEmail(list.get(i).getEmail());
                member.setAddress(list.get(i).getAddress());
                member.setDistrict(list.get(i).getDistrict());
                member.setCity(list.get(i).getCity());
                member.setState(list.get(i).getState());
                member.setCountry(list.get(i).getCountry());
                member.setPosition(list.get(i).getPosition());
                member.setImage(list.get(i).getImage());
            }
        }
        model.addAttribute("member", member);
        return "Details/details-member";
    }

    @PostMapping("/members/update-member/{id}")
    public String updateMember(@RequestParam("imagee")MultipartFile multipartFile, Member member) throws IOException {
        System.out.println(member.getImage());
        // String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        // System.out.println(fileName);
        // memberService.updateMember(member.getId(), member);
        // String uploadDir= "src/main/resources/static/image/user-photos/"+ member.getId();
        // FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
        return "redirect:/rest/api/member/members/{id}";
    }
    
    @RequestMapping("/members/delete-member/{id}")
    public String deleteMember(@PathVariable String id){
        memberService.deleteMember(id);
        return "redirect:/rest/api/member/members";
    }

}