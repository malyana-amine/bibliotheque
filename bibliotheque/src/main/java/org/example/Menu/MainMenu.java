package org.example.Menu;

import org.example.Services.BookService;
import org.example.Services.DemandeService;

import java.util.Scanner;

public class MainMenu {
    public static void menu() throws Exception {
        Scanner scanner = new Scanner(System.in);

        BookService bookService = new BookService();
        DemandeService demandeService = new DemandeService();

        while (true) {

            System.out.println("1. Add Book");
            System.out.println("2. Modify book");
            System.out.println("3. Delete book");
            System.out.println("4. Search book");
            System.out.println("5. Display book details");
            System.out.println("6. Borrow book");
            System.out.println("7. Return book");
            System.out.println("8. Statistiques de demandes");
            System.out.print("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {

                case 1:
                    bookService.addBook();
                    break;
                case 2:
                    bookService.updateBook();
                    break;
                case 3:
                    bookService.deleteBook();
                    break;
                case 4:
                    bookService.searchBook();
                    break;
                case 5:
                    bookService.displayAllBooks();
                    break;
                case 6:
                    demandeService.addDemande();
                    break;
                case 7:
                    demandeService.returned();
                    break;
                case 8:
                    demandeService.displayDemandeStatistics();
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
