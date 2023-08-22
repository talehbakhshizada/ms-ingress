package az.company.msingress.dao.repository;

import az.company.msingress.dao.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findByPostId(Long postId);
}