package az.company.msingress.dao.repository;

import az.company.msingress.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //@Query(value = "SELECT u FROM UserEntity u JOIN FETCH u.articles")
    //@EntityGraph(type = EntityGraph.EntityGraphType.FETCH , value = "user_entity_graph")
    @EntityGraph(attributePaths = {"articles"})
    List<UserEntity> findByNameContaining(String name);
}
