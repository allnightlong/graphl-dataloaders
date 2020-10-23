package ru.megadevelopers.graphql.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

@KeySpace("product_review_replies")
@Data
public class ProductReviewReply {
   @Id private Long id;
    private String user;
    private String body;
    private ProductReview productReview;
}
