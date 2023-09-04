package org.example.Services;


import org.example.Entities.Auteur;
import org.example.Entities.Book;
import org.example.Repositories.AuteurRepository;
import org.example.Repositories.BookRepository;

import java.util.Scanner;

public class AdminService {

    Scanner scanner = new Scanner(System.in);
    AuteurRepository auteurRepository = new AuteurRepository();
    BookRepository bookRepository = new BookRepository();
    public void addAuteur(){

        Auteur auteur = new Auteur();

        System.out.print("Enter person's name: ");
        auteur.setName(scanner.nextLine());
        auteurRepository.Insert(auteur);


    }

    public void addBook(){
        Book book = new Book();

        System.out.print("Enter book title: ");
        book.setTitle(scanner.nextLine());

        System.out.print("Enter book quantity: ");
        book.setQuantitytotal(scanner.nextInt());

        System.out.print("Enter book prix: ");
        book.setPrix(scanner.nextFloat());

        System.out.print("Enter book auteur: ");
        String authorName = scanner.nextLine();
        Auteur auteur = new Auteur(authorName);
        book.setAuteur(auteur);
        bookRepository.Insert(book);


    }


}
