package ru.megadevelopers.graphql.dataloaders;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.model.Product;
import ru.megadevelopers.graphql.model.ProductReview;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Profile("dataloaders")
public class ProductDataLoaderResolver implements GraphQLResolver<Product> {

    @SuppressWarnings("unused")
    public CompletableFuture<List<ProductReview>> reviews(Product product, DataFetchingEnvironment env) {
        DefaultGraphQLContext context = env.getContext();
        DataLoaderRegistry registry = context.getDataLoaderRegistry().orElseThrow();
        DataLoader<Product, List<ProductReview>> reviewDataLoader = registry.getDataLoader("productReviewDataLoader");

        return reviewDataLoader.load(product);
    }
}
