package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.BookStatus;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.utils.CustomPage;
import com.oekrem.mikroservices.utils.ServiceUriResolver;
import com.oekrem.mikroservices.dto.BookResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookGatewayController {

    private final WebClient.Builder webClientBuilder;
    private final ServiceUriResolver serviceUriResolver;
    private String bookUri;

    @PostConstruct
    public void init() {
        this.bookUri = serviceUriResolver.getServiceUri("book-service", BookGatewayController.class);
        webClientBuilder.baseUrl(bookUri);
    }

    @GetMapping
    public Mono<ResponseEntity<CustomPage<BookResponse>>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false)BookStatus status
            ) {

        return webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .queryParamIfPresent("title", Optional.ofNullable(title))
                        .queryParamIfPresent("status", Optional.ofNullable(status).map(Enum::name))
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<BookResponse>>() {})
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<BookResponse> getBooksById(@PathVariable Long id) {
        String url = UriComponentsBuilder.fromUriString(bookUri)
                .path("/" + id.toString())
                .toUriString();

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(BookResponse.class);

    }

    @PostMapping("/books")
    public Mono<BookResponse> saveBook(@RequestBody CreateBookRequest createBookRequest) {
        return webClientBuilder.build()
                .post()
                .uri(bookUri)
                .bodyValue(createBookRequest)
                .retrieve()
                .bodyToMono(BookResponse.class);
    }

    @PutMapping("/books/{id}")
    public Mono<BookResponse> updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest updateBookRequest) {
        String url = UriComponentsBuilder.fromUriString(bookUri)
                .path("/" + id.toString())
                .toUriString();

        return webClientBuilder.build()
                .put()
                .uri(url)
                .bodyValue(updateBookRequest)
                .retrieve()
                .bodyToMono(BookResponse.class);
    }

    @PatchMapping("/books/{id}")
    public Mono<BookResponse> patchBook(@PathVariable Long id, @RequestBody UpdateBookRequest updateBookRequest) {
        String url = UriComponentsBuilder.fromUriString(bookUri)
                .path("/" + id.toString())
                .toUriString();

        return webClientBuilder.build()
                .patch()
                .uri(url)
                .bodyValue(updateBookRequest)
                .retrieve()
                .bodyToMono(BookResponse.class);
    }

    @DeleteMapping("/books/{id}")
    public Mono<Void> deleteBook(@PathVariable Long id){
        String url = UriComponentsBuilder.fromUriString(bookUri)
                .path("/" + id)
                .toUriString();

        return webClientBuilder.build()
                .delete()
                .uri(url)
                .retrieve()
                .bodyToMono(void.class);
    }

}
