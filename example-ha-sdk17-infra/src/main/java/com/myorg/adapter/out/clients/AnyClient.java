package com.myorg.adapter.out.clients;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Setter
public class AnyClient {

    private WebClient webClient;
    private String host;

    private static final String GET_ENDPOINT = "/get";
    private static final String POST_ENDPOINT = "/post";

    public Mono<Object> getRequest() {
        return webClient
                .get()
                .uri(GET_ENDPOINT)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> postRequest(Object body) {
        return webClient
                .post()
                .uri(POST_ENDPOINT)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public String returnHost() {
        return this.host;
    }


}
