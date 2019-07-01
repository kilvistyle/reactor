package com.example.reactor.controller;

import com.example.reactor.entity.*;
import com.example.reactor.exception.AppException;
import com.example.reactor.resource.Entry;
import com.example.reactor.resource.HeadersWithCategory;
import com.example.reactor.resource.HeadersByUser;
import com.example.reactor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>BlogController</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/23 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/23
 */
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
    public Mono<List<Category>> findCategories() {
        return categoryService.findAll().log("categories");
    }

    @GetMapping("/headers/find-by-user/{userId}")
    public Mono<HeadersByUser> findHeadersByUserId(@NotNull @PathVariable final String userId) {
        return userService.read(userId)
                .flatMap(user -> headerService.findByUserId(user.getUserId())
                        .map(headers -> HeadersByUser.builder()
                                .user(user)
                                .headers(headers)
                                .build()));
//
//        return userService.read(userId)
//                .flatMap(user -> {
//                    if (User.UserState.VALID.equals(user.getUserState())) {
//                        return headerService.findByUserId(user.getUserId())
//                                .map(headers -> HeadersByUser.builder()
//                                        .user(user)
//                                        .headers(headers)
//                                        .build());
//                    }
//                    else {
//                        throw new AppException("userId is invalid.", HttpStatus.BAD_REQUEST);
//                    }
//                })
//                .log("headers/find-by-user");
    }

    @GetMapping("/headers/find-by-category/{categoryId}")
    public Mono<HeadersWithCategory> findHeadersByCategoryId(@NotNull @PathVariable final String categoryId) {
        return Mono.zip(
                    categoryService.read(categoryId),          // T1
                    headerService.findByCategoryId(categoryId) // T2
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
                            bodyService.read(header.getEntryId()),              // T1
                            commentService.findByEntryId(header.getEntryId()) // T2
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
                )
                .log(String.format("entries/%d", entryId));

//        return headerService.read(entryId)
//                .flatMap(header -> Mono.zip(
//                        Mono.just(header),
//                        bodyService.read(header.getEntryId()),
//                        commentService.findByEntryId(header.getEntryId())
//                        )
//                )
//                .map(tuple3 -> {
//                    final Header header = tuple3.getT1();
//                    final Body body = tuple3.getT2();
//                    final List<Comment> comments = tuple3.getT3();
//                    return Entry.builder()
//                            .header(header)
//                            .body(body)
//                            .comments(comments)
//                            .build();
//                });
    }

}
