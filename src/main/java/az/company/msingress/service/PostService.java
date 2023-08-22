package az.company.msingress.service;

import az.company.msingress.dao.entity.PostEntity;
import az.company.msingress.dao.repository.PostRepository;
import az.company.msingress.exception.NotFoundException;
import az.company.msingress.mapper.PostMapper;

import az.company.msingress.model.constants.ErrorMessages;
import az.company.msingress.model.request.CreateCommentRequest;
import az.company.msingress.model.request.CreatePostRequest;
import az.company.msingress.model.response.PostResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static az.company.msingress.mapper.factory.CommentFactory.*;
import static az.company.msingress.mapper.factory.PostDetailFactory.*;
import static az.company.msingress.mapper.factory.PostFactory.*;
import static az.company.msingress.model.constants.ErrorMessages.CARD_NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class PostService {

      PostRepository postRepository;
      TagService tagService;

    public void createPost(CreatePostRequest request) {
        var post = buildPostEntity(request);
        var postDetail = buildPostDetailEntity(post, request.getCreatedBy());

        if (!request.getTagIds().isEmpty()) {
            var tags = tagService.getTagsByIds(request.getTagIds());
            post.setTags(tags);
        }

        post.setDetail(postDetail);
        postRepository.save(post);
    }

    public void addCommentToPost(Long id, CreateCommentRequest request) {
        var post = fetchPostIfExist(id);
        var comments = post.getComments();

        comments.add(buildCommentEntity(post, request.getContent()));
        post.setComments(comments);

        postRepository.save(post);
    }

    public PostResponse getPost(Long id) {
        var post = fetchPostIfExist(id);
        return PostMapper.mapPostEntityToPostResponse(post);
    }

    private PostEntity fetchPostIfExist(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.valueOf(ErrorMessages.POST_NOT_FOUND),
                        String.format(ErrorMessages.POST_NOT_FOUND.getMessage(), id)));
    }
}