package com.example.reactor.service;

import com.example.reactor.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p>CategoryService</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/24 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/24
 */
@Service
public class CategoryService {

    private static final String BACKENDS_BASE_URL = "http://localhost:8081";

    private final WebClient webClient = WebClient.create(BACKENDS_BASE_URL);

    public Flux<Category> findAll() {
        return webClient.get()
                .uri("/categories")
                .retrieve()
                .bodyToFlux(Category.class);
    }

    public Mono<Category> read(String categoryId) {
        return webClient.get()
                .uri("/categories/{categoryId}", categoryId)
                .retrieve()
                .bodyToMono(Category.class);
    }
}
