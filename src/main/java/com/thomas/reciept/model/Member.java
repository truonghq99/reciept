package com.thomas.reciept.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
    
    @NotBlank(message = "Enter your username")
    private String username;
    @NotBlank(message = "Enter your password")
    @Min(value = 8, message = "Password required at least 8 characters")
    private String password;

    private String firstName;
    private String lastName;
    
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private Date DateOfBirth;
    private String phoneNumber;
    @Email(message = "Email address is not valid")
    private String email;

    private String address;
    private String district;
    private String city;
    private String state;
    private String country;

    private String position="member";

    private String image;

}
