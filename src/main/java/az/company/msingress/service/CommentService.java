package az.company.msingress.service;

import az.company.msingress.dao.entity.CommentEntity;
import az.company.msingress.dao.repository.CommentRepository;
import az.company.msingress.exception.NotFoundException;
import az.company.msingress.mapper.CommentMapper;
import az.company.msingress.model.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.company.msingress.model.constants.ErrorMessages.COMMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void changeCommentViewStatus(Long id) {
        var comment = fetchCommentIfExist(id);
        comment.setViewed(!comment.isViewed());
        commentRepository.save(comment);
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        var comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(CommentMapper::mapCommentEntityToCommentResponse)
                .collect(Collectors.toList());
    }

    public void changeCommentContent(Long id, String content) {
        var comment = fetchCommentIfExist(id);
        if (comment.isViewed()) comment.setViewed(false);
        comment.setContent(content);
        commentRepository.save(comment);
    }

    private CommentEntity fetchCommentIfExist(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.valueOf(COMMENT_NOT_FOUND),
                        String.format(COMMENT_NOT_FOUND.getMessage(), id)));
    }
}
