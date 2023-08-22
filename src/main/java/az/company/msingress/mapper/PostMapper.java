package az.company.msingress.mapper;

import az.company.msingress.dao.entity.PostEntity;
import az.company.msingress.model.response.PostResponse;

public class PostMapper {
    public static PostResponse mapPostEntityToPostResponse(PostEntity entity) {
        return new PostResponse(entity.getId(), entity.getTitle());
    }
}
