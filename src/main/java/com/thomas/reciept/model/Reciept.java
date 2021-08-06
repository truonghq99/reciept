package com.thomas.reciept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reciept {
    @Id
    private String id;
    private float price;
    private float discount;
    private float totalPrice;
    private Supplier supplier;
    private List<RecieptItem> listItem;
}
