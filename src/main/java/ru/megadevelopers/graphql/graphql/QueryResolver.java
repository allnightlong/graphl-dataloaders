package ru.megadevelopers.graphql.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.model.Product;
import ru.megadevelopers.graphql.service.ProductService;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    private final ProductService productService;

    public QueryResolver(ProductService productService) {
        this.productService = productService;
    }

    @SuppressWarnings("unused")
    public List<Product> products() {
        return productService.findAll();
    }
}
