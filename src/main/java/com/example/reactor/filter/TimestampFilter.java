package com.example.reactor.filter;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Order(1)
public class TimestampFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange).compose(call -> doFilter(exchange, call));
    }

    private Publisher<Void> doFilter(ServerWebExchange exchange, Mono<Void> call) {
        return Mono
                // before
                .fromRunnable(() -> doBeforeRequest(exchange))
                // do request
                .then(call)
                // after (success)
                .doOnSuccess((done) -> doAfterRequest(exchange))
                // after (with error)
                .doOnError((throwable -> doAfterRequestWithError(exchange, throwable)));
    }

    private void doBeforeRequest(ServerWebExchange exchange) {
        String uri = exchange.getRequest().getURI().toString();
        long start = System.currentTimeMillis();
        log.info(String.format("%s [IN]: %d", uri, start));
    }

    private void doAfterRequest(ServerWebExchange exchange) {
        String uri = exchange.getRequest().getURI().toString();
        long end = System.currentTimeMillis();
        log.info(String.format("%s [OUT]: %d", uri, end));
    }

    private void doAfterRequestWithError(ServerWebExchange exchange, Throwable throwable) {
        String uri = exchange.getRequest().getURI().toString();
        long end = System.currentTimeMillis();
        log.info(String.format("%s [OUT]: %d with Error(%s)", uri, end, throwable.toString()));
    }
}
