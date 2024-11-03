package ru.vagapov.spring.mappingUtils;

import org.springframework.stereotype.Component;
import ru.vagapov.spring.dto.Book;
import ru.vagapov.spring.entity.BookEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BookMapper {

    public BookEntity bookDtoToBookEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setCostOfBook(book.getCostOfBook());
        bookEntity.setRentedDate(book.getRentedDate());
        bookEntity.setReturnedDate(book.getReturnedDate());
        bookEntity.setAmount(book.getAmount());
        bookEntity.setRented(book.isRented());
        bookEntity.setTimesOfRent(book.getTimesOfRent());
        //bookEntity.setUsers(mappingUtils.listOfUserDtoToSetOfUserEntity(book.getUsers()));
        return bookEntity;
    }

    public Book bookEntityToBookDto(BookEntity bookEntity) {
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setTitle(bookEntity.getTitle());
        book.setAuthor(bookEntity.getAuthor());
        book.setCostOfBook(bookEntity.getCostOfBook());
        book.setRentedDate(bookEntity.getRentedDate());
        book.setReturnedDate(bookEntity.getReturnedDate());
        book.setAmount(bookEntity.getAmount());
        book.setRented(book.isRented());
        book.setTimesOfRent(bookEntity.getTimesOfRent());
        return book;
    }
    public List<Long> setOfBookEntityToBooksDto(Set<BookEntity> bookEntity) {
        List<Long> bookIds = new ArrayList<>();
        for (BookEntity bookEntity1 : bookEntity) {
            bookIds.add(bookEntity1.getId());
        }
        return bookIds;
    }


    public Set<BookEntity> listOfBookDtoToSetOfBookEntity(List<Book> books) {
        Set<BookEntity> bookEntitySet = new HashSet<>();
        for (Book book : books) {
            bookEntitySet.add(bookDtoToBookEntity(book));
        }
        return bookEntitySet;
    }

    public List<Book> setOfBookEntityToListOfBookDto(Set<BookEntity> bookEntitySet) {
        List<Book> bookList = new ArrayList<>();
        for (BookEntity bookEntity : bookEntitySet) {
            bookList.add(bookEntityToBookDto(bookEntity));
        }
        return bookList;
    }
}
