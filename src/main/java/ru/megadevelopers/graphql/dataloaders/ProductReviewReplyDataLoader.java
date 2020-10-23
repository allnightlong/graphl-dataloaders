package ru.megadevelopers.graphql.dataloaders;

import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.model.ProductReviewReply;
import ru.megadevelopers.graphql.service.ProductReviewReplyService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.stream.Collectors.groupingBy;

@Component
public class ProductReviewReplyDataLoader implements MappedBatchLoader<ProductReview, List<ProductReviewReply>> {
    private final ProductReviewReplyService productReviewReplyService;

    public ProductReviewReplyDataLoader(ProductReviewReplyService productReviewReplyService) {
        this.productReviewReplyService = productReviewReplyService;
    }

    @Override
    public CompletionStage<Map<ProductReview, List<ProductReviewReply>>> load(Set<ProductReview> reviews) {
        return CompletableFuture.supplyAsync(() ->
                productReviewReplyService
                        .findByProductReviewIn(reviews)
                        .stream()
                        .collect(groupingBy(ProductReviewReply::getProductReview)));
    }
}
