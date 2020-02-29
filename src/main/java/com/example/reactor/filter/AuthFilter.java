package com.example.reactor.filter;

import com.example.reactor.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
//@Component
//@Order(2)
public class AuthFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange).compose(call -> doFilter(exchange, call));
    }

    private Publisher<Void> doFilter(ServerWebExchange exchange, Mono<Void> call) {
        return exchange.getSession().flatMap(webSession -> {
            Map<String, Object> attributes = webSession.getAttributes();
            User user = (User) attributes.get(User.class.getName());
            // セッションにユーザ情報がない場合
            if (user == null) {
//                // 401:UnauthorizedエラーとしてJSONレスポンスを生成
//                ServerHttpResponse response = exchange.getResponse();
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
//                String body = "{\"message\":\"ログイン認証が必要です。\"}";
//                DataBufferFactory dbf = response.bufferFactory();
//                return response.writeWith(Mono.just(dbf.wrap(body.getBytes())));
                throw new RuntimeException("ログイン認証が必要です。");
            }
            // ユーザ情報がある場合
            else {
                return call;
            }
        });
    }
}
