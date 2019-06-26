package com.example.reactor.service;

import com.example.reactor.entity.Header;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p>HeaderService</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/23 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/23
 */
@Service
public class HeaderService {

    private static final String BACKENDS_BASE_URL = "http://localhost:8081";

    private final WebClient webClient = WebClient.create(BACKENDS_BASE_URL);

    private final ParameterizedTypeReference<Header> headerParameterizedTypeReference =
            new ParameterizedTypeReference<Header>() {};

    private final ParameterizedTypeReference<List<Header>> headersParameterizedTypeReference =
            new ParameterizedTypeReference<List<Header>>() {};

    public Mono<Header> create(Header header) {
        return webClient.post()
                .uri("/headers")
                .body(BodyInserters.fromObject(header))
                .retrieve()
                .bodyToMono(headerParameterizedTypeReference);
    }

    public Mono<Header> read(Long articleId) {
        return webClient.get()
                .uri("/headers/{articleId}", articleId)
                .retrieve()
                .bodyToMono(headerParameterizedTypeReference);
    }

    public Mono<Header> update(Header header) {
        return webClient.put()
                .uri("/headers")
                .body(BodyInserters.fromObject(header))
                .retrieve()
                .bodyToMono(headerParameterizedTypeReference);
    }

    public Mono<Integer> delete(Long articleId, Long version) {
        return webClient.delete()
                .uri("/headers/{articleId}?version={version}",articleId, version)
                .retrieve()
                .bodyToMono(Integer.class);
    }

    public Mono<List<Header>> findRecently() {
        return webClient.get()
                .uri("/headers/find?recently=10")
                .retrieve()
                .bodyToMono(headersParameterizedTypeReference);
    }

    public Mono<List<Header>> findByCategoryId(String categoryId) {
        return webClient.get()
                .uri(String.format("/headers/find?categoryId=%s", categoryId))
                .retrieve()
                .bodyToMono(headersParameterizedTypeReference);
    }

    public Mono<List<Header>> findByUserId(String userId) {
        return webClient.get()
                .uri(String.format("/headers/find?userId=%s", userId))
                .retrieve()
                .bodyToMono(headersParameterizedTypeReference);
    }
}
