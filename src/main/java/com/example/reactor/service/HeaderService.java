package com.example.reactor.service;

import com.example.reactor.entity.Header;
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

    public Mono<Header> create(Header header) {
        return webClient.post()
                .uri("/headers")
                .body(BodyInserters.fromObject(header))
                .retrieve()
                .bodyToMono(Header.class);
    }

    public Mono<Header> read(Long entryId) {
        return webClient.get()
                .uri("/headers/{entryId}", entryId)
                .retrieve()
                .bodyToMono(Header.class);
    }

    public Mono<Header> update(Header header) {
        return webClient.put()
                .uri("/headers")
                .body(BodyInserters.fromObject(header))
                .retrieve()
                .bodyToMono(Header.class);
    }

    public Mono<Integer> delete(Long entryId, Long version) {
        return webClient.delete()
                .uri("/headers/{entryId}?version={version}",entryId, version)
                .retrieve()
                .bodyToMono(Integer.class);
    }

    public Mono<List<Header>> findRecently() {
        return webClient.get()
                .uri("/headers/find?recently=10")
                .retrieve()
                .bodyToFlux(Header.class)
                .collectList();
    }

    public Mono<List<Header>> findByCategoryId(String categoryId) {
        return webClient.get()
                .uri(String.format("/headers/find?categoryId=%s", categoryId))
                .retrieve()
                .bodyToFlux(Header.class)
                .collectList();
    }

    public Mono<List<Header>> findByUserId(String userId) {
        return webClient.get()
                .uri(String.format("/headers/find?userId=%s", userId))
                .retrieve()
                .bodyToFlux(Header.class)
                .collectList();
    }
}
