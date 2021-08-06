package com.thomas.reciept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "item")
public class Book extends Item{

    private String author;
    private String publisher;
    private String category;
}
