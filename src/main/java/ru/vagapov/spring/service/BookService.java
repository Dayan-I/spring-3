package ru.vagapov.spring.service;

import ru.vagapov.spring.dto.Book;
import ru.vagapov.spring.dto.User;

import java.util.List;

public interface BookService {
    /**
     * Поиск книги по айди
     *
     * @param id - айди книги, которую надо найти
     * @return Book искомая книга
     */

    Book findById(Long id);

    /**
     * Поиск всех пользователей
     *
     * @return List<User> список всех пользователей
     */
    List<Book> findAll();



    List<Book> findAllNonRentedBooks();


    void updateBook(Book book);

    void changeAmountOfBook(String bookName);

    boolean checkUserForHavingBook(List<Long> bookIds, Long bookId );
}


