package com.example.reactor.service;

import com.example.reactor.entity.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryService {

    @Value("${url.backends.root}")
    private String URL_BACKENDS_ROOT;

    private final WebClient webClient = WebClient.create();

    public Flux<Category> findAll() {
        return webClient.get()
                .uri(URL_BACKENDS_ROOT + "/categories")
                .retrieve()
                .bodyToFlux(Category.class);
    }

    public Mono<Category> read(String categoryId) {
        return webClient.get()
                .uri(URL_BACKENDS_ROOT + "/categories/{categoryId}", categoryId)
                .retrieve()
                .bodyToMono(Category.class);
    }
}
