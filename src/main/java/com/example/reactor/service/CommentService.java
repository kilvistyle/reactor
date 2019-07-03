package com.example.reactor.service;

import com.example.reactor.entity.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;

@Service
public class CommentService {

    @Value("${url.backends.root}")
    private String URL_BACKENDS_ROOT;

    private final WebClient webClient = WebClient.create();

    public Flux<Comment> findByEntryId(@NotNull Long entryId) {
        return webClient.get()
                .uri(URL_BACKENDS_ROOT + "/comments/{entryId}", entryId)
                .retrieve()
                .bodyToFlux(Comment.class);
    }

}
