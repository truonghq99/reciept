package com.thomas.reciept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Item {

    @Transient
    public static final String SEQUENCE_NAME = "item_sequence";

    @Id
    private int id;
    @NotEmpty
    private String title;
    private String type="";
    private String brand;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mfgDate;
    private float price;
    private int quantity=0;
    private String active="false";
    
}
