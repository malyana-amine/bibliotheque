package org.example.Entities;

public class Auteur {

    private int id;
    private String name;

    public Auteur(String name) {
        this.name = name;
    }

    public Auteur(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Auteur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
