package az.company.msingress.mapper.factory;

import az.company.msingress.dao.entity.PostEntity;
import az.company.msingress.model.request.CreatePostRequest;

public class PostFactory {
    public static PostEntity buildPostEntity(CreatePostRequest request) {
        return PostEntity.builder()
                .title(request.getTitle())
                .build();
    }
}