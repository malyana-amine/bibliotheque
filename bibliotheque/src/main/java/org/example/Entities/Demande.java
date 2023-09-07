package org.example.Entities;

import java.time.LocalDate;

public class Demande {

    private int id;
    private Users user;
    private Book book;
    private LocalDate startdate;
    private LocalDate enddate;
    private int quantity;
    private boolean returned;

    public Demande(Users user, Book book, LocalDate startdate, LocalDate enddate, int quantity, boolean returned) {
        this.user = user;
        this.book = book;
        this.startdate = startdate;
        this.enddate = enddate;
        this.quantity = quantity;
        this.returned = returned;
    }

    public Demande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
