package az.company.msingress.mapper.factory;

import az.company.msingress.dao.entity.PostDetailEntity;
import az.company.msingress.dao.entity.PostEntity;

public class PostDetailFactory {
    public static PostDetailEntity buildPostDetailEntity(PostEntity post, String createdBy) {
        return PostDetailEntity.builder()
                .post(post)
                .createdBy(createdBy)
                .build();
    }
}
