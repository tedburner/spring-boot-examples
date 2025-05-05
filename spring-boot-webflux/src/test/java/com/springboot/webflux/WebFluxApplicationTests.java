package com.springboot.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebFluxApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void contextLoads() {
        webTestClient
                .get().uri("/hello")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello, Spring");

    }

    @Test
    public void webClientTest() {
        Mono<String> response = WebClient.create()
                .method(HttpMethod.GET)
                .uri("https://testicbc.chedai0.com/page/statistics/total")
                .cookie("token", "a78c221b-4db5-4dcd-99d3-6a28380529bc")
                //.cookie("JSESSIONID","47EA0C70C31264CD0D954DAC1B1CF735; FACEVIEW_TOKEN=a78c221b-4db5-4dcd-99d3-6a28380529bc")
                .retrieve()
                .bodyToMono(String.class);
        log.info("result: {}", response.block());
    }

}

