package com.example.reactor.service;

import com.example.reactor.entity.Comment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    private final ParameterizedTypeReference<List<Comment>> commentsParameterizedTypeReference =
            new ParameterizedTypeReference<List<Comment>>() {};

    public Mono<List<Comment>> findByEntryId(@NotNull Long entryId) {
        return webClient.get()
                .uri("/comments/{entryId}", entryId)
                .retrieve()
                .bodyToMono(commentsParameterizedTypeReference);
    }

}
