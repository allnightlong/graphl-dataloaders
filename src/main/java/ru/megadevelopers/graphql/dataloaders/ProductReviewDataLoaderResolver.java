package ru.megadevelopers.graphql.dataloaders;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.model.ProductReviewReply;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Profile("dataloaders")
public class ProductReviewDataLoaderResolver implements GraphQLResolver<ProductReview> {

    @SuppressWarnings("unused")
    public CompletableFuture<List<ProductReviewReply>> replies(ProductReview productReview, DataFetchingEnvironment env) {
        DefaultGraphQLContext context = env.getContext();
        DataLoaderRegistry registry = context.getDataLoaderRegistry().orElseThrow();
        DataLoader<ProductReview, List<ProductReviewReply>> productReviewReplyDataLoader = registry.getDataLoader("productReviewReplyDataLoader");

        return productReviewReplyDataLoader.load(productReview);
    }
}
