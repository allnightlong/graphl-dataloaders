package ru.megadevelopers.graphql.graphql;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.model.ProductReviewReply;
import ru.megadevelopers.graphql.service.ProductReviewReplyService;

import java.util.List;

@Component
@Profile("default")
public class ProductReviewResolver implements GraphQLResolver<ProductReview> {
    private final ProductReviewReplyService productReviewReplyService;

    public ProductReviewResolver(ProductReviewReplyService productReviewReplyService) {
        this.productReviewReplyService = productReviewReplyService;
    }

    @SuppressWarnings("unused")
    public List<ProductReviewReply> replies(ProductReview productReview) {
        return productReviewReplyService.findByProductReview(productReview);
    }
}
