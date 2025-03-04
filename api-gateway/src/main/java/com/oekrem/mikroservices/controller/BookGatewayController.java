package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookGatewayController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping
    public Mono<String> getBooks() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/v1/books")  // eğer ki service discovery kullanırsan bunlara gerek kalmıyor
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/{id}")
    public Mono<String> getBooksById(@PathVariable UUID id) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8082/api/v1/books")
                .path("/" + id.toString())
                .toUriString();

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping("/books")
    public Mono<String> saveBook(@RequestBody CreateBookRequest createBookRequest) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/api/v1/books")
                .bodyValue(createBookRequest)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PutMapping("/books/{id}")
    public Mono<String> updateBook(@PathVariable UUID id, @RequestBody UpdateBookRequest updateBookRequest) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8082/api/v1/books")
                .path("/" + id.toString())
                .toUriString();

        return webClientBuilder.build()
                .put()
                .uri(url)
                .bodyValue(updateBookRequest)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PatchMapping("/books/{id}")
    public Mono<String> patchBook(@PathVariable UUID id, @RequestBody UpdateBookRequest updateBookRequest) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8082/api/v1/books")
                .path("/" + id.toString())
                .toUriString();

        return webClientBuilder.build()
                .patch()
                .uri(url)
                .bodyValue(updateBookRequest)
                .retrieve()
                .bodyToMono(String.class);
    }

    @DeleteMapping("/books/{id}")
    public Mono<String> deleteBook(@PathVariable UUID id){
        String url = UriComponentsBuilder.fromUriString("http://localhost:8082/api/v1/books")
                .path("/" + id)
                .toUriString();

        return webClientBuilder.build()
                .delete()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }


}
