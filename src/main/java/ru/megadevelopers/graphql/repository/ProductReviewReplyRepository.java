package ru.megadevelopers.graphql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.model.ProductReviewReply;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductReviewReplyRepository extends CrudRepository<ProductReviewReply, Long> {
    List<ProductReviewReply> findByProductReview(ProductReview productReview);

    List<ProductReviewReply> findByProductReviewIn(Collection<ProductReview> reviews);
}
