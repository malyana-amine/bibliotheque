package org.example.Services;

import org.example.Entities.Auteur;
import org.example.Entities.Book;
import org.example.Repositories.AuteurRepository;
import org.example.Repositories.BookRepository;

import java.util.List;
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
        scanner.nextLine();
        book.setIsbn(scanner.nextLine());

        System.out.print("Enter book author: ");

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
        scanner.nextLine();

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

    public void searchBook() throws Exception {
        System.out.print("Enter search term (title, ID, or author): ");
        String searchTerm = scanner.nextLine();

        List<Book> foundBooks = bookRepository.searchBook(searchTerm);

        if (foundBooks.isEmpty()) {
            System.out.println("No books found matching the search term: " + searchTerm);
        } else {
            System.out.println("Found books:");
            for (Book book : foundBooks) {
                System.out.println(book.getId() + " - " + book.getTitle() + " by " + book.getAuteur().getName());

            }
        }
    }

    public void displayAllBooks() {
        try {
            List<Book> allBooks = bookRepository.getAllBooks();

            if (allBooks.isEmpty()) {
                System.out.println("No books found in the database.");
            } else {
                System.out.println("All Books:");

                for (Book book : allBooks) {
                    System.out.println("ID: " + book.getId());
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Quantity Total: " + book.getQuantitytotal());
                    System.out.println("Quantity Dispo: " + book.getQuantitydispo());
                    System.out.println("Book Missing: " + book.getBookmissing());
                    System.out.println("Price: " + book.getPrix());
                    System.out.println("ISBN: " + book.getIsbn());
                    System.out.println("Author: " + book.getAuteur().getName());

                    System.out.println("------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving and displaying books: " + e.getMessage());
        }
    }


}

