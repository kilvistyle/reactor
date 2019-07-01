package com.example.reactor.service;

import com.example.reactor.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;

/**
 * <p>CommentService</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@Service
public class CommentService {

    private static final String BACKENDS_BASE_URL = "http://localhost:8081";

    private final WebClient webClient = WebClient.create(BACKENDS_BASE_URL);

    public Flux<Comment> findByEntryId(@NotNull Long entryId) {
        return webClient.get()
                .uri("/comments/{entryId}", entryId)
                .retrieve()
                .bodyToFlux(Comment.class);
    }

}
