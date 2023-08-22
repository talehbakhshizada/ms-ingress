package az.company.msingress.dao.repository;

import az.company.msingress.dao.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<TagEntity,Long> {
    List<TagEntity> findByIdIn(Set<Long> ids);
}