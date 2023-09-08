package org.example.Services;

import org.example.Entities.Book;
import org.example.Entities.Demande;
import org.example.Entities.Users;
import org.example.Repositories.BookRepository;
import org.example.Repositories.DemandeRepository;
import org.example.Repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DemandeService {

    Demande demande = new Demande();
    Scanner scanner = new Scanner(System.in);
    public void addDemande() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add your phone:");
        String phone = scanner.nextLine();

        UserRepository userRepository = new UserRepository();
        Users user = userRepository.findByPhone(phone);

        if (user == null) {
            user = new Users();
            user.setPhone(phone);

            System.out.println("Add your full name:");
            user.setFullname(scanner.nextLine());

            System.out.println("Add your email:");
            user.setEmail(scanner.nextLine());

            user = userRepository.insert(user);

        }

        BookRepository bookRepository = new BookRepository();

        try {
            List<Book> allBooks = bookRepository.getAllBooks();

            if (allBooks.isEmpty()) {
                System.out.println("No books found in the database.");
            } else {
                System.out.println("All Books:");

                for (Book book : allBooks) {
                    System.out.println("ID: " + book.getId());
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Quantity Dispo: " + book.getQuantitydispo());
                    System.out.println("ISBN: " + book.getIsbn());
                    System.out.println("------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving and displaying books: " + e.getMessage());
        }

        int bookId;
        Book book = null;

        while (book == null) {
            System.out.println("Add your book ID: ");
            try {
                bookId = Integer.parseInt(scanner.nextLine());
                book = bookRepository.findBook(bookId);

                if (book == null) {
                    System.out.println("Book not found. Please enter a valid book ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid book ID as a number.");
            }
        }

        System.out.println("Book found: " + book.getTitle());


        System.out.println("Enter start date (yyyy-MM-dd):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter end date (yyyy-MM-dd):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        boolean returned = false;


        Demande demande = new Demande(user, book, startDate, endDate, quantity, returned);


        DemandeRepository demandeRepository = new DemandeRepository();
        demandeRepository.InsertDemande(demande);

        System.out.println("Demande added successfully.");

    }


}
