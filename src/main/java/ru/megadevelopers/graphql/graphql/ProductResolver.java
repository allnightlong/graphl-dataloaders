package ru.megadevelopers.graphql.graphql;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.model.Product;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.service.ProductReviewService;

import java.util.List;

@Component
@Profile("default")
public class ProductResolver implements GraphQLResolver<Product> {
    private final ProductReviewService productReviewService;

    public ProductResolver(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @SuppressWarnings("unused")
    public List<ProductReview> reviews(Product product) {
        return productReviewService.findByProduct(product);
    }
}
