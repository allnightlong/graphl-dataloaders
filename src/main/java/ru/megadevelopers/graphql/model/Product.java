package ru.megadevelopers.graphql.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

@KeySpace("products")
@Data
public class Product {
    @Id private Long id;
    private String name;
    private String brand;
}
