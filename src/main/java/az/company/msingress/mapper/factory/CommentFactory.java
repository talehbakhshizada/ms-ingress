package az.company.msingress.mapper.factory;

import az.company.msingress.dao.entity.CommentEntity;
import az.company.msingress.dao.entity.PostEntity;

public class CommentFactory {
    public static CommentEntity buildCommentEntity(PostEntity post, String content) {
        return CommentEntity.builder()
                .post(post)
                .content(content)
                .build();
    }
}
