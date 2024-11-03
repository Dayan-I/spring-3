package ru.vagapov.spring.controller;

import org.springframework.web.bind.annotation.*;
import ru.vagapov.spring.dto.Book;
import ru.vagapov.spring.service.BookService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }


    @GetMapping("/books/users/{id}")
    public List<Book> getBooksByUserId(@PathVariable Long id) {
        return null;
    }




}
