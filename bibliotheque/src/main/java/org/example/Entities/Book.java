package org.example.Entities;

public class Book {

    private int id;
    private String title;
    private int quantitytotal;
    private int quantitydispo;
    private int bookmissing;
    private float prix;
    private Auteur auteur;

    public Book(String title, int quantitytotal, int quantitydispo, int bookmissing, float prix, Auteur auteur) {
        this.title = title;
        this.quantitytotal = quantitytotal;
        this.quantitydispo = quantitydispo;
        this.bookmissing = bookmissing;
        this.prix = prix;
        this.auteur = auteur;
    }
    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantitytotal() {
        return quantitytotal;
    }

    public void setQuantitytotal(int quantitytotal) {
        this.quantitytotal = quantitytotal;
    }

    public int getQuantitydispo() {
        return quantitydispo;
    }

    public void setQuantitydispo(int quantitydispo) {
        this.quantitydispo = quantitydispo;
    }

    public int getBookmissing() {
        return bookmissing;
    }

    public void setBookmissing(int bookmissing) {
        this.bookmissing = bookmissing;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
