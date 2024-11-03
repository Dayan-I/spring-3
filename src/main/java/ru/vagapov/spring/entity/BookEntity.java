package ru.vagapov.spring.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private Long amount;

    @Column
    private Long costOfBook;

    @Column
    private LocalDate rentedDate;

    @Column
    private LocalDate returnedDate;

    @Column
    private boolean isRented;

    @Column
    private Long timesOfRent;

    @ManyToMany
    @JoinTable(name = "users_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<UserEntity> users;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return isRented == that.isRented && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(amount, that.amount) && Objects.equals(costOfBook, that.costOfBook) && Objects.equals(rentedDate, that.rentedDate) && Objects.equals(returnedDate, that.returnedDate) && Objects.equals(timesOfRent, that.timesOfRent) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, amount, costOfBook, rentedDate, returnedDate, isRented, timesOfRent, users);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", amount=" + amount +
               ", costOfBook=" + costOfBook +
               ", rentedDate=" + rentedDate +
               ", returnedDate=" + returnedDate +
               ", isRented=" + isRented +
               ", timesOfRent=" + timesOfRent +
               ", users=" + users +
               '}';
    }
}
