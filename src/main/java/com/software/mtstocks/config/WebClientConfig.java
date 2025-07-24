package com.software.mtstocks.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .filter(logRequest())
                .build();
    }


    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            log.info("[Request] {} {}", request.method(), request.url());
            request.headers().forEach((name, values) -> values.forEach(value ->
                    log.info("[Header] {}: {}", name, value)
            ));
            return Mono.just(request);
        });
    }

}
