package com.example.reactor.service;

import com.example.reactor.entity.Body;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * <p>BodyService</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@Service
public class BodyService {

    private static final String BACKENDS_BASE_URL = "http://localhost:8081";

    private final WebClient webClient = WebClient.create(BACKENDS_BASE_URL);

    public Mono<Body> read(@NotNull Long entryId) {
        return webClient.get()
                .uri("/bodies/{entryId}", entryId)
                .retrieve()
                .bodyToMono(Body.class);
    }

}
