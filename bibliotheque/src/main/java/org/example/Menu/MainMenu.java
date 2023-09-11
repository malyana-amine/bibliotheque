package org.example.Menu;

import org.example.Config.Config;
import org.example.Entities.Auteur;
import org.example.Services.AdminService;
import org.example.Services.BookService;
import org.example.Services.DemandeService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    public static void menu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        BookService bookService = new BookService();
        DemandeService demandeService = new DemandeService();

        while (true) {
            System.out.println("1. Add auteur");
            System.out.println("2. Add Book");
            System.out.println("3. Modify book");
            System.out.println("4. Delete book");
            System.out.println("5. Search book");
            System.out.println("6. Display book details");
            System.out.println("7. Borrow book");
            System.out.println("8. Return book");
            System.out.println("9. Statistiques de demandes");
            System.out.print("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminService.addAuteur();
                    break;
                case 2:
                    bookService.addBook();
                    break;
                case 3:
                    bookService.updateBook();
                    break;
                case 4:
                    bookService.deleteBook();
                    break;
                case 5:
                    bookService.searchBook();
                    break;
                case 6:
                    bookService.displayAllBooks();
                    break;
                case 7:
                    demandeService.addDemande();
                    break;
                case 8:
                    demandeService.returned();
                    break;
                case 9:
                    demandeService.displayDemandeStatistics();
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
