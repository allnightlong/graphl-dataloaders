package ru.megadevelopers.graphql.config;

import com.github.javafaker.Faker;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.megadevelopers.graphql.model.Product;
import ru.megadevelopers.graphql.model.ProductReview;
import ru.megadevelopers.graphql.model.ProductReviewReply;
import ru.megadevelopers.graphql.repository.ProductRepository;
import ru.megadevelopers.graphql.repository.ProductReviewReplyRepository;
import ru.megadevelopers.graphql.repository.ProductReviewRepository;

import java.util.stream.IntStream;

@Component
public class FakeDataBootstrap implements ApplicationRunner {
    private final Faker faker = new Faker();

    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewReplyRepository productReviewReplyRepository;

    public FakeDataBootstrap(ProductRepository productRepository, ProductReviewRepository productReviewRepository, ProductReviewReplyRepository productReviewReplyRepository) {
        this.productRepository = productRepository;
        this.productReviewRepository = productReviewRepository;
        this.productReviewReplyRepository = productReviewReplyRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        IntStream
                .rangeClosed(1, 10)
                .forEach(x -> createProduct());
    }

    private void createProduct() {
        Product product = new Product();
        product.setName(faker.funnyName().name());
        product.setBrand(faker.funnyName().name());
        productRepository.save(product);

        IntStream
                .rangeClosed(1, 10)
                .forEach(x -> createReview(product));
    }

    private void createReview(Product product) {
        ProductReview productReview = new ProductReview();
        productReview.setTitle(faker.rockBand().name());
        productReview.setBody(faker.book().title());
        productReview.setProduct(product);
        productReviewRepository.save(productReview);

        IntStream
                .rangeClosed(1, 10)
                .forEach(x -> createReviewReply(productReview));
    }

    private void createReviewReply(ProductReview productReview) {
        ProductReviewReply productReviewReply = new ProductReviewReply();
        productReviewReply.setUser(faker.book().author());
        productReviewReply.setBody(faker.book().title());
        productReviewReply.setProductReview(productReview);
        productReviewReplyRepository.save(productReviewReply);
    }
}
