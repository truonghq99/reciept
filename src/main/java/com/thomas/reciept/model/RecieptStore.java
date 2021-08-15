package com.thomas.reciept.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class RecieptStore {

    @Id
    private String id;

    private Store store;
    private Date date;
    private List<Item> listItem;
    private float totalCost;
}
