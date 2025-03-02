package top.whr.requestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ConsumerController3 {
    private final String SERVICE_URL = "http://localhost:8080";
    private final String SERVICE_URL1 = "https://www.wanandroid.com";

    private WebClient webClient = WebClient.builder()
            .baseUrl(SERVICE_URL1)
            .build();

    @GetMapping("/webClientTest")
    public String webClientTest() {
        Mono<String> mono = webClient
                .get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
        mono.subscribe(System.out::println);
        return "请求成功3";
    }

    @GetMapping("/webClientTest1")
    public Mono<String> webClientTest1(@RequestParam Integer page) {
        return webClient.get()
                .uri("/project/list/" + page + "/json?cid=294")
                .retrieve()
                .bodyToMono(String.class);
    }
}
