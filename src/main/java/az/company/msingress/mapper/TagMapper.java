package az.company.msingress.mapper;

import az.company.msingress.dao.entity.TagEntity;
import az.company.msingress.model.request.CreateTagRequest;
import az.company.msingress.model.response.TagResponse;

public class TagMapper {
    public static TagEntity mapCreateTagRequestToTagEntity(CreateTagRequest request) {
        return TagEntity.builder()
                .name(request.getName())
                .build();
    }

    public static TagResponse mapTagEntityToTagResponse(TagEntity entity) {
        return TagResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}