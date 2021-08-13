package com.thomas.reciept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Supplier {
    @Transient
    public static final String SEQUENCE_NAME = "supplier_sequence";

    @Id
    private int id;

    private String company;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cooperateDate;

}
