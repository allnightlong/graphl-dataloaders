package ru.megadevelopers.graphql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.model.ProductReviewReply;
import ru.megadevelopers.graphql.repository.ProductReviewReplyRepository;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ProductReviewReplyService {
    private final ProductReviewReplyRepository productReviewReplyRepository;

    public ProductReviewReplyService(ProductReviewReplyRepository productReviewReplyRepository) {
        this.productReviewReplyRepository = productReviewReplyRepository;
    }

    public  List<ProductReviewReply> findByProductReview(ProductReview productReview) {
        log.info("Requesting replies for review: {}", productReview.getId());

        return productReviewReplyRepository.findByProductReview(productReview);
    }

    public  List<ProductReviewReply> findByProductReviewIn(Collection<ProductReview> reviews) {
        log.info("Requesting replies for reviews: {}", reviews.stream().map(ProductReview::getId).collect(toList()));

        return productReviewReplyRepository.findByProductReviewIn(reviews);
    }
}
