package az.company.msingress.controller;

import az.company.msingress.model.request.CreateTagRequest;
import az.company.msingress.model.response.TagResponse;
import az.company.msingress.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTag(@RequestBody CreateTagRequest createTagRequest) {
        tagService.createTag(createTagRequest);
    }

    @GetMapping
    public List<TagResponse> getTags() {
        return tagService.getTags();
    }
}
