package ru.megadevelopers.graphql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;
import ru.megadevelopers.graphql.model.Product;
import ru.megadevelopers.graphql.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        log.info("Requesting product");

        return IterableConverter.toList(productRepository.findAll());
    }

}
