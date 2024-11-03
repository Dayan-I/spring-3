package ru.vagapov.spring.dto;

import java.time.LocalDate;
import java.util.List;

public class Book {

    private Long id;

    private String title;

    private String author;

    private Long amount;

    private Long costOfBook;

    private LocalDate rentedDate;

    private LocalDate returnedDate;

    private boolean isRented;

    private Long timesOfRent;

    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCostOfBook() {
        return costOfBook;
    }

    public void setCostOfBook(Long costOfBook) {
        this.costOfBook = costOfBook;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public Long getTimesOfRent() {
        return timesOfRent;
    }

    public void setTimesOfRent(Long timesOfRent) {
        this.timesOfRent = timesOfRent;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
