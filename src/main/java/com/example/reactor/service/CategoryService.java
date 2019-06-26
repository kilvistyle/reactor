package com.example.reactor.service;

import com.example.reactor.entity.Category;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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
@Component
public class CategoryService {

    private static final String BACKENDS_BASE_URL = "http://localhost:8081";

    private final WebClient webClient = WebClient.create(BACKENDS_BASE_URL);

    private final ParameterizedTypeReference<List<Category>> categoriesParameterizedTypeReference =
            new ParameterizedTypeReference<List<Category>>() {};

    private static final ParameterizedTypeReference<Category> categoryParameterizedTypeReference =
            new ParameterizedTypeReference<Category>() {};

    public Mono<List<Category>> findAll() {
        return webClient.get()
                .uri("/categories")
                .retrieve()
                .bodyToMono(categoriesParameterizedTypeReference);
    }

    public Mono<Category> read(String categoryId) {
        return webClient.get()
                .uri(String.format("/categories/%s", categoryId))
                .retrieve()
                .bodyToMono(categoryParameterizedTypeReference);
    }
}
