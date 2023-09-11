package org.example.Services;

import org.example.Entities.Book;
import org.example.Entities.Demande;
import org.example.Entities.Users;
import org.example.Repositories.BookRepository;
import org.example.Repositories.DemandeRepository;
import org.example.Repositories.UserRepository;

import org.example.Entities.Demande;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

        LocalDate startDate = LocalDate.now();

        System.out.println("Enter end date (yyyy-MM-dd):");
        LocalDate endDate = null;

        while (endDate == null) {
            try {
                String endDateStr = scanner.nextLine();
                endDate = LocalDate.parse(endDateStr);

                if (endDate.isBefore(startDate)) {
                    System.out.println("End date must be after or equal to the current date.");
                    endDate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        boolean returned = false;

        Demande demande = new Demande(user, book, startDate, endDate, quantity, returned);

        DemandeRepository demandeRepository = new DemandeRepository();
        demandeRepository.InsertDemande(demande);

        System.out.println("Demande added successfully.");
    }

    public void returned() throws Exception {

            System.out.println("Enter the Demande ID to return: ");
            int demandeId = Integer.parseInt(scanner.nextLine());

            DemandeRepository demandeRepository = new DemandeRepository();
            demandeRepository.markDemandeAsReturned(demandeId);
        }


      //  public void displayDemandeStatistics(List<Demande> demandeList) {
      //      int totalDemandes = demandeList.size();
       //     int returnedDemandes = 0;
      //      int totalQuantity = 0;

     //       for (Demande demande : demandeList) {
      //          if (demande.isReturned()) {
      //              returnedDemandes++;
      //          }
       //         totalQuantity += demande.getQuantity();
      //      }

       //     System.out.println("Demande Statistics:");
       //     System.out.println("Total Demandes: " + totalDemandes);
       //     System.out.println("Returned Demandes: " + returnedDemandes);
      //      System.out.println("Total Quantity Requested: " + totalQuantity);
      //  }

    public void displayDemandeStatistics() {
        DemandeRepository demandeRepository = new DemandeRepository();
        List<Demande> demandeList = demandeRepository.getAllDemande();
        // Create maps to store statistics
        Map<Users, Integer> userDemandeCount = new HashMap<>();
        Map<Book, Integer> bookDemandeCount = new HashMap<>();
        Map<LocalDate, Integer> startDateCount = new HashMap<>();
        Map<LocalDate, Integer> endDateCount = new HashMap<>();

        // Calculate statistics
        for (Demande demande : demandeList) {
            // User statistics
            Users user = demande.getUser();
            userDemandeCount.put(user, userDemandeCount.getOrDefault(user, 0) + 1);

            // Book statistics
            Book book = demande.getBook();
            bookDemandeCount.put(book, bookDemandeCount.getOrDefault(book, 0) + 1);

            // Start date statistics
            LocalDate startDate = demande.getStartdate();
            startDateCount.put(startDate, startDateCount.getOrDefault(startDate, 0) + 1);

            // End date statistics
            LocalDate endDate = demande.getEnddate();
            endDateCount.put(endDate, endDateCount.getOrDefault(endDate, 0) + 1);
        }

        // Display user statistics
        System.out.println("User Statistics:");
        for (Map.Entry<Users, Integer> entry : userDemandeCount.entrySet()) {
            Users user = entry.getKey();
            int demandeCount = entry.getValue();
            System.out.println(user.getFullname() + ": " + demandeCount + " demandes");
        }

        // Display book statistics
        System.out.println("\nBook Statistics:");
        for (Map.Entry<Book, Integer> entry : bookDemandeCount.entrySet()) {
            Book book = entry.getKey();
            int demandeCount = entry.getValue();
            System.out.println(book.getTitle() + ": " + demandeCount + " demandes");
        }

        // Display start date statistics
        System.out.println("\nStart Date Statistics:");
        for (Map.Entry<LocalDate, Integer> entry : startDateCount.entrySet()) {
            LocalDate startDate = entry.getKey();
            int demandeCount = entry.getValue();
            System.out.println(startDate + ": " + demandeCount + " demandes");
        }

        // Display end date statistics
        System.out.println("\nEnd Date Statistics:");
        for (Map.Entry<LocalDate, Integer> entry : endDateCount.entrySet()) {
            LocalDate endDate = entry.getKey();
            int demandeCount = entry.getValue();
            System.out.println(endDate + ": " + demandeCount + " demandes");
        }
    }

}
