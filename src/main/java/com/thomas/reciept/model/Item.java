package com.thomas.reciept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Item {
    @Id
    private int id;

    private String title;
    private String type;
    private Date mfgDate;
    private float price;

}
