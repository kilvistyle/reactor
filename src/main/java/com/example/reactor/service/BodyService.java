package com.example.reactor.service;

import com.example.reactor.entity.Body;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Service
public class BodyService {

    @Value("${url.backends.root}")
    private String URL_BACKENDS_ROOT;

    private final WebClient webClient = WebClient.create();

    public Mono<Body> read(@NotNull Long entryId) {
        return webClient.get()
                .uri(URL_BACKENDS_ROOT + "/bodies/{entryId}", entryId)
                .retrieve()
                .bodyToMono(Body.class);
    }

}
