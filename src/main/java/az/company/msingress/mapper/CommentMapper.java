package az.company.msingress.mapper;

import az.company.msingress.dao.entity.CommentEntity;
import az.company.msingress.model.response.CommentResponse;

public class CommentMapper {
    public static CommentResponse mapCommentEntityToCommentResponse(CommentEntity entity) {
        return CommentResponse.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .viewed(entity.isViewed())
                .build();
    }
}