package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.bookDto.BookStatus;
import com.oekrem.mikroservices.dto.bookDto.CreateBookRequest;
import com.oekrem.mikroservices.dto.bookDto.UpdateBookRequest;
import com.oekrem.mikroservices.utils.CustomPage;
import com.oekrem.mikroservices.utils.ServiceUriResolver;
import com.oekrem.mikroservices.dto.bookDto.BookResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Optional;

@OpenAPIDefinition(info = @Info(
        title = "API Gateway - BookService",
        description = "Allows communication to book-service",
        version = "1.0",
        contact = @Contact(name = "Ekrem", email = "oekremyildirim@outlook.com")
))
@Tag(name = "Gateway API - BookService", description = "Allows communication to book-service")
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
    @Operation(summary = "Get All Books", description = "Returns a Book Page")
    @ApiResponse(responseCode = "200", description = "Succesful", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
    public Mono<CustomPage<BookResponse>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) BookStatus status
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
                .bodyToMono(new ParameterizedTypeReference<CustomPage<BookResponse>>() {});
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Book by id", description = "Path: id")
    @ApiResponse(responseCode = "200", description = "Succesful", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
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

    @PostMapping
    @Operation(summary = "Create new Book", description = "Request: title, author, publisher, publishedDate, pages, language, description, category_id, stock, rating, edition")
    @ApiResponse(responseCode = "201", description = "Created succesfully", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
    @ApiResponse(responseCode = "404", description = "Already exist")
    public Mono<BookResponse> saveBook(@RequestBody CreateBookRequest createBookRequest) {
        return webClientBuilder.build()
                .post()
                .uri(bookUri)
                .bodyValue(createBookRequest)
                .retrieve()
                .bodyToMono(BookResponse.class);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the Book", description = "Path: id, Request: title, author, publisher, publishedDate, pages, language, description, category_id, stock, rating, edition")
    @ApiResponse(responseCode = "200", description = "Succesful", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
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

    @PatchMapping("/{id}")
    @Operation(summary = "Patch the Book", description = "Path: id, Request: title, author, publisher, publishedDate, pages, language, description, category_id, stock, rating, edition")
    @ApiResponse(responseCode = "200", description = "Succesful", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the Book", description = "Path: id")
    @ApiResponse(responseCode = "204", description = "Succesful")
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
