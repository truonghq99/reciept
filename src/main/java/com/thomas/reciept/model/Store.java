package com.thomas.reciept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Store {
    @Transient
    public static final String SEQUENCE_NAME ="store_sequence";

    @Id
    private int id;

    private String address;
    private String phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date openDate;
    private String status="pending";
}
