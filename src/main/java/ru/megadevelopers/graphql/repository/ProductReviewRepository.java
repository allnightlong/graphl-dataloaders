package ru.megadevelopers.graphql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.megadevelopers.graphql.model.Product;
import ru.megadevelopers.graphql.model.ProductReview;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductReviewRepository extends CrudRepository<ProductReview, Long> {
    // where product_id = :id
    List<ProductReview> findByProduct(Product product);

    // where product_id in (:ids)
    List<ProductReview> findByProductIn(Collection<Product> products);
}
