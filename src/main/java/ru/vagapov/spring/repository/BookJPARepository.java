package ru.vagapov.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vagapov.spring.entity.BookEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookJPARepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findById(Long id);

    @Query("SELECT b from BookEntity b where b.isRented = false ")
    List<BookEntity> findNotRentedBooks();
}
