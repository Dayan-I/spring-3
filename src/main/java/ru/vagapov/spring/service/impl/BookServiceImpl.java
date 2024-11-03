package ru.vagapov.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.vagapov.spring.dto.Book;
import ru.vagapov.spring.entity.BookAmountEntity;
import ru.vagapov.spring.entity.BookEntity;
import ru.vagapov.spring.mappingUtils.BookMapper;
import ru.vagapov.spring.repository.BookAmountJPARepository;
import ru.vagapov.spring.repository.BookJPARepository;
import ru.vagapov.spring.service.BookService;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookJPARepository bookJPARepository;
    private final BookMapper bookMapper;
    private final BookAmountJPARepository bookAmountJPARepository;

    public BookServiceImpl(BookJPARepository bookJPARepository, BookMapper bookMapper, BookAmountJPARepository bookAmountJPARepository) {
        this.bookJPARepository = bookJPARepository;
        this.bookMapper = bookMapper;
        this.bookAmountJPARepository = bookAmountJPARepository;
    }

    @Override
    public Book findById(Long id) {
        Book book = bookMapper.bookEntityToBookDto(bookJPARepository.findById(id).get());
        return book;
    }

    /**
     * Поиск всех пользователей
     *
     * @return List<User> список всех пользователей
     */
    @Override
    public List<Book> findAll() {
        List<BookEntity> books = bookJPARepository.findAll();
        List<Book> bookList = new ArrayList<Book>();
        for (BookEntity book : books) {
            bookList.add(bookMapper.bookEntityToBookDto(book));
        }
        return bookList;
    }

    @Override
    public List<Book> findAllNonRentedBooks() {
       List<BookEntity> books = bookJPARepository.findNotRentedBooks();
        List<Book> bookList = new ArrayList<Book>();
        for (BookEntity book : books) {
            bookList.add(bookMapper.bookEntityToBookDto(book));
        }
        return bookList;
    }

    @Override
    public void updateBook(Book book) {
        BookEntity bookEntity = bookMapper.bookDtoToBookEntity(book);
        bookJPARepository.save(bookEntity);
    }

    @Override
    public void changeAmountOfBook(String bookName) {
        BookAmountEntity bookAmountEntity = bookAmountJPARepository.findByTitle(bookName);
        bookAmountEntity.setRentalAmount(bookAmountEntity.getRentalAmount() + 1);
        bookAmountJPARepository.save(bookAmountEntity);
    }

    @Override
    public boolean checkUserForHavingBook(List<Long> bookIds, Long bookId) {
        BookEntity bookEntity = bookJPARepository.findById(bookId).get();
        int i = 0;
        for(Long id :bookIds){
            BookEntity book = bookJPARepository.findById(id).get();
            if(book.getTitle().equals(bookEntity.getTitle())){
                i+=1;
            }
        }
        return i > 0;
    }
}
