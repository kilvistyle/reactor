package com.example.reactor.service;


import com.example.reactor.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <p>UserService</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@Service
public class UserService {

    private static final String BACKENDS_BASE_URL = "http://localhost:8081";

    private final WebClient webClient = WebClient.create(BACKENDS_BASE_URL);

    private final ParameterizedTypeReference<User> userParameterizedTypeReference =
            new ParameterizedTypeReference<User>() {};

    public Mono<User> read(String userId) {
        return webClient.get()
                .uri("/users/{userId}", userId)
                .retrieve()
                .bodyToMono(userParameterizedTypeReference);
    }

}
