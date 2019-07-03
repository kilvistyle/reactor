package com.example.reactor.service;

import com.example.reactor.entity.Header;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeaderService {

    @Value("${url.backends.root}")
    private String URL_BACKENDS_ROOT;

    private final WebClient webClient = WebClient.create();

    public Mono<Header> read(Long entryId) {
        return webClient.get()
                .uri(URL_BACKENDS_ROOT + "/headers/{entryId}", entryId)
                .retrieve()
                .bodyToMono(Header.class);
    }

    public Flux<Header> findByCategoryId(String categoryId) {
        return webClient.get()
                .uri(String.format(URL_BACKENDS_ROOT + "/headers/find?categoryId=%s", categoryId))
                .retrieve()
                .bodyToFlux(Header.class);
    }

    public Flux<Header> findByUserId(String userId) {
        return webClient.get()
                .uri(String.format(URL_BACKENDS_ROOT + "/headers/find?userId=%s", userId))
                .retrieve()
                .bodyToFlux(Header.class);
    }
}
