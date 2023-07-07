package az.company.msingress.dao.repository;

import az.company.msingress.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardRepository extends JpaRepository<CardEntity,Long>, JpaSpecificationExecutor<CardEntity> {
}
