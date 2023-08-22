package az.company.msingress.service;

import az.company.msingress.dao.entity.TagEntity;
import az.company.msingress.dao.repository.TagRepository;
import az.company.msingress.mapper.TagMapper;
import az.company.msingress.model.request.CreateTagRequest;
import az.company.msingress.model.response.TagResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static az.company.msingress.mapper.TagMapper.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class TagService {
      TagRepository tagRepository;

    public void createTag(CreateTagRequest  createTagRequest) {
        tagRepository.save(mapCreateTagRequestToTagEntity(createTagRequest));
    }

    public List<TagEntity> getTagsByIds(Set<Long> ids) {
        return tagRepository.findByIdIn(ids);
    }

    public List<TagResponse> getTags() {
        return tagRepository.findAll().stream()
                .map(TagMapper::mapTagEntityToTagResponse)
                .collect(Collectors.toList());
    }
}