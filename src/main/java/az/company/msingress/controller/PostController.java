package az.company.msingress.controller;

import az.company.msingress.model.request.CreateCommentRequest;
import az.company.msingress.model.request.CreatePostRequest;
import az.company.msingress.model.response.PostResponse;
import az.company.msingress.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody CreatePostRequest createPostRequest) {
        postService.createPost(createPostRequest);
    }

    @PatchMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCommentToPost(@PathVariable Long id,
                                 @RequestBody CreateCommentRequest createCommentRequest) {
        postService.addCommentToPost(id, createCommentRequest);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
}
