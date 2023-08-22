package az.company.msingress.controller;

import az.company.msingress.model.response.CommentResponse;
import az.company.msingress.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PatchMapping("/{id}/view-status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeCommentViewStatus(@PathVariable Long id) {
        commentService.changeCommentViewStatus(id);
    }

    @GetMapping("/posts/{postId}")
    public List<CommentResponse> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @PatchMapping("/{id}/content")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeCommentContent(@PathVariable Long id, @RequestParam String value) {
        commentService.changeCommentContent(id, value);
    }
}