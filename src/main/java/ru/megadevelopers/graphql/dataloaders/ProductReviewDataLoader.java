package ru.megadevelopers.graphql.dataloaders;

import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.model.Product;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.service.ProductReviewService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.stream.Collectors.groupingBy;

@Component
public class ProductReviewDataLoader implements MappedBatchLoader<Product, List<ProductReview>> {
    private final ProductReviewService productReviewService;

    public ProductReviewDataLoader(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }


    @Override
    public CompletionStage<Map<Product, List<ProductReview>>> load(Set<Product> products) {
        return CompletableFuture.supplyAsync(() ->
                productReviewService
                        .findByProductIn(products)
                        .stream()
                        .collect(groupingBy(ProductReview::getProduct)));
    }
}
