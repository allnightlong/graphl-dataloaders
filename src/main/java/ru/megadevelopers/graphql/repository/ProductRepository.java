package ru.megadevelopers.graphql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.megadevelopers.graphql.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
