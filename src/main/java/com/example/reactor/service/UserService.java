package com.example.reactor.service;


import com.example.reactor.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Value("${url.backends.root}")
    private String URL_BACKENDS_ROOT;

    private final WebClient webClient = WebClient.create();

    public Mono<User> read(String userId) {
        return webClient.get()
                .uri(URL_BACKENDS_ROOT + "/users/{userId}", userId)
                .retrieve()
                .bodyToMono(User.class);
    }

}
