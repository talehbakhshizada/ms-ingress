package az.company.msingress.dao.repository;

import az.company.msingress.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
