package ru.megadevelopers.graphql.config;


import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.dataloaders.ProductReviewDataLoader;
import ru.megadevelopers.graphql.dataloaders.ProductReviewReplyDataLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    private final ProductReviewDataLoader productReviewDataLoader;
    private final ProductReviewReplyDataLoader productReviewReplyDataLoader;

    public CustomGraphQLContextBuilder(ProductReviewDataLoader productReviewDataLoader, ProductReviewReplyDataLoader productReviewReplyDataLoader) {
        this.productReviewDataLoader = productReviewDataLoader;
        this.productReviewReplyDataLoader = productReviewReplyDataLoader;
    }

    @Override
    public GraphQLContext build(HttpServletRequest req, HttpServletResponse response) {
        return DefaultGraphQLServletContext.createServletContext(dataLoaderRegistry(), null).with(req).with(response).build();
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(dataLoaderRegistry(), null);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest request) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext(dataLoaderRegistry(), null).with(session).with(request).build();
    }

    public DataLoaderRegistry dataLoaderRegistry() {
        DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
        dataLoaderRegistry.register("productReviewDataLoader", DataLoader.newMappedDataLoader(productReviewDataLoader));
        dataLoaderRegistry.register("productReviewReplyDataLoader", DataLoader.newMappedDataLoader(productReviewReplyDataLoader));
        return dataLoaderRegistry;
    }
}
