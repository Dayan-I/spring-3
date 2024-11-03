package ru.vagapov.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vagapov.spring.entity.BookAmountEntity;

public interface BookAmountJPARepository extends JpaRepository<BookAmountEntity, Long> {

    BookAmountEntity findByTitle(String title);
}
