package com.thomas.reciept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class RecieptItem {
    @Id
    private int id;

    private int quantity;
    private float price;
    private float discount;
    private float totalPrice;

    private Item item;
}
