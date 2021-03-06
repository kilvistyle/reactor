package com.example.reactor.controller;

import com.example.reactor.entity.*;
import com.example.reactor.resource.Entry;
import com.example.reactor.resource.HeadersWithCategory;
import com.example.reactor.resource.HeadersByUser;
import com.example.reactor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HeaderService headerService;

    @Autowired
    private UserService userService;

    @Autowired
    private BodyService bodyService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/categories")
    public Flux<Category> findCategories() {
        return categoryService.findAll().log("categories");
    }

    @GetMapping("/headers/find-by-user/{userId}")
    public Mono<HeadersByUser> findHeadersByUserId(@NotNull @PathVariable final String userId) {
        return userService.read(userId)
                .flatMap(user -> headerService.findByUserId(user.getUserId())
                        .collectList()
                        .map(headers -> HeadersByUser.builder()
                                .user(user)
                                .headers(headers)
                                .build()));
    }

    @GetMapping("/headers/find-by-category/{categoryId}")
    public Mono<HeadersWithCategory> findHeadersByCategoryId(@NotNull @PathVariable final String categoryId) {
        return Mono.zip(
                    categoryService.read(categoryId), // T1
                    headerService.findByCategoryId(categoryId).collectList() // T2
                )
                .map(tuple2 -> {
                    final Category category = tuple2.getT1();
                    final List<Header> headers = tuple2.getT2();
                    return HeadersWithCategory.builder()
                            .category(category)
                            .headers(headers)
                            .build();
                });
    }

    @GetMapping("/entries/{entryId}")
    public Mono<Entry> getEntry(@NotNull @PathVariable Long entryId) {
        return headerService.read(entryId)
                .flatMap(header -> Mono.zip(
                            bodyService.read(header.getEntryId()), // T1
                            commentService.findByEntryId(header.getEntryId()).collectList() // T2
                        )
                        .map(tuple2 -> {
                            final Body body = tuple2.getT1();
                            final List<Comment> comments = tuple2.getT2();
                            return Entry.builder()
                                    .header(header)
                                    .body(body)
                                    .comments(comments)
                                    .build();
                        })
                );
    }

}
