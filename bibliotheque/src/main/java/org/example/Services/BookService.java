package org.example.Services;

import org.example.Entities.Auteur;
import org.example.Entities.Book;
import org.example.Repositories.AuteurRepository;
import org.example.Repositories.BookRepository;

import java.util.Scanner;

public class BookService {
    BookRepository bookRepository = new BookRepository();
    Scanner scanner = new Scanner(System.in);

    public void addBook() throws Exception {
        Book book = new Book();

        System.out.print("Enter book title: ");
        book.setTitle(scanner.nextLine());

        System.out.print("Enter book quantity: ");
        book.setQuantitytotal(scanner.nextInt());

        System.out.print("Enter book prix: ");
        book.setPrix(scanner.nextFloat());

        System.out.print("Enter book author: ");
        scanner.nextLine(); // Consume the newline character
        String authorName = scanner.nextLine();


        AuteurRepository auteurRepository = new AuteurRepository();

        Auteur author = auteurRepository.findByName(authorName);
        System.out.println(author.getId()+"sdfsf");
        book.setAuteur(author);
        bookRepository.Insert(book);


    }
}

