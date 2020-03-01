package com.example.reactor.filter;

import com.example.reactor.entity.User;
import com.example.reactor.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@Order(1)
public class AuthFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange).compose(call -> doFilter(exchange, call));
    }

    private Publisher<Void> doFilter(ServerWebExchange exchange, Mono<Void> call) {
        return exchange.getSession().flatMap(webSession -> {
            Map<String, Object> attributes = webSession.getAttributes();
            // セッションからユーザ情報を取得
            Optional<User> optionalUser =
                    Optional.ofNullable((User)attributes.get(User.class.getName()));
            return optionalUser
                    // ユーザがある場合はリクエストを処理
                    .map(user -> call)
                    // ユーザが無い場合は未認証エラーをスロー
                    .orElseThrow(UnauthorizedException::new);
        });
    }
}
