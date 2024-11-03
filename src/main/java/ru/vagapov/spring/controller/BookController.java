package ru.vagapov.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vagapov.spring.dto.Book;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.mappingUtils.BookMapper;
import ru.vagapov.spring.service.BookService;
import ru.vagapov.spring.service.UserService;

import java.util.List;

@RequestMapping("/books")
@Controller
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, UserService userService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.userService = userService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public String home(Model model, @RequestParam(name = "bookname", required = false) String username) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/users")
    public String usersWithBooks(Model model, @RequestParam(name = "id") String id) {
        List<User> users = userService.findAllUsersByBookId(Long.parseLong(id));
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/rent_book")
    public String user(Model model, @RequestParam(name = "id") String id) {
        User user = userService.findById(Long.parseLong(id));
        List<Book> books = bookService.findAllNonRentedBooks();
        model.addAttribute("books", books);
        model.addAttribute("user", user);
        return "rent_book";
    }

    @PatchMapping("/rent")
    public String rent(@RequestParam(name = "user_id") String user_id, @RequestParam(name = "book_id") String book_id) {
        User user = userService.findById(Long.parseLong(user_id));
        List<Long> books = user.getBooks();
        Book book = bookService.findById(Long.parseLong(book_id));
        if (bookService.checkUserForHavingBook(books, Long.parseLong(book_id))) {
            return "redirect:/error";
        }
        books.add(book.getId());
        user.setBooks(books);
        book.setRented(true);
        userService.updateUser(user);
        bookService.updateBook(book);
        bookService.changeAmountOfBook(book.getTitle());
        return "redirect:/books";
    }
}
