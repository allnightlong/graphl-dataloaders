package ru.megadevelopers.graphql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.megadevelopers.graphql.model.Product;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.repository.ProductReviewRepository;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ProductReviewService {
    private final ProductReviewRepository productReviewRepository;

    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    public List<ProductReview> findByProduct(Product product) {
        log.info("Requesting reviews for product: {}", product.getId());

        return productReviewRepository.findByProduct(product);
    }

    public List<ProductReview> findByProductIn(Collection<Product> products) {
        log.info("Requesting reviews for products: [{}]", products.stream().map(Product::getId).collect(toList()));

        return productReviewRepository.findByProductIn(products);
    }
}
