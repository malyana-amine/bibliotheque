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

        System.out.print("Enter book isbn: ");
        scanner.nextLine(); // Consume the newline character
        book.setIsbn(scanner.nextLine());

        System.out.print("Enter book author: ");
       // scanner.nextLine(); // Consume the newline character
        String authorName = scanner.nextLine();





        AuteurRepository auteurRepository = new AuteurRepository();

        Auteur author = auteurRepository.findByName(authorName);
        System.out.println(author.getId()+"sdfsf");
        book.setAuteur(author);
        bookRepository.Insert(book);
    }
    public void updateBook() throws Exception {
        System.out.print("Enter book ISBN: ");
        int isbn = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        BookRepository bookRepository = new BookRepository();
        Book foundBook = bookRepository.findBook(isbn);

        if (foundBook == null) {
            System.out.println("Book not found with ISBN: " + isbn);
            return;
        }

        System.out.print("Enter new book title (or press Enter to keep the current title): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            foundBook.setTitle(newTitle);
        }

        System.out.print("Enter new book quantity (or press Enter to keep the current quantity): ");
        String quantityInput = scanner.nextLine();
        if (!quantityInput.isEmpty()) {
            int newQuantity = Integer.parseInt(quantityInput);
            foundBook.setQuantitytotal(newQuantity);
        }

        System.out.print("Enter new book price (or press Enter to keep the current price): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            float newPrice = Float.parseFloat(priceInput);
            foundBook.setPrix(newPrice);
        }

        System.out.print("Enter book isbn: ");
        String newIsbn = scanner.nextLine();
        foundBook.setIsbn(newIsbn);

        // Update the book in the database
        bookRepository.update(foundBook);
        System.out.println("Book updated successfully!");
    }


    public void deleteBook() throws Exception {
        System.out.print("Enter book ISBN: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();

        BookRepository bookRepository = new BookRepository();
        bookRepository.delete(isbn);
    }


}

