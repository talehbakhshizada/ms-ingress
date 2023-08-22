package az.company.msingress.dao.repository;

import az.company.msingress.dao.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
