package com.thomas.reciept.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    @Id
    private String id;
    
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private Date DateOfBirth;
    private String phoneNumber;
    private String email;

    private String address;
    private String districtName;
    private String city;
    private String state;
    private String country;

    private String position="member";
}
