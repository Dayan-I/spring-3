package ru.vagapov.spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bookAmount")
public class BookAmountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String title;

    @Column
    private Long totalAmount;

    @Column
    private Long rentalAmount;

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

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(Long rentalAmount) {
        this.rentalAmount = rentalAmount;
    }
}
