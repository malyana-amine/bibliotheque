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




}
