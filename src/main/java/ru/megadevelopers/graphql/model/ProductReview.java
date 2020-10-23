package ru.megadevelopers.graphql.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

@KeySpace("product_reviews")
@Data
public class ProductReview {
    @Id private Long id;
    private String title;
    private String body;
    private Product product;
}
