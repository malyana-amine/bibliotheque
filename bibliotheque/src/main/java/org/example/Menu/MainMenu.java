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


    public static void LibraryLogo(){
        System.out.println("█████████████████████████████████████████████████████████████████████████████\n");

        System.out.println(" ▄█        ▄█  ▀█████████▄     ▄████████    ▄████████    ▄████████ ▄██   ▄   \n" +
                "███       ███    ███    ███   ███    ███   ███    ███   ███    ███ ███   ██▄ \n" +
                "███       ███▌   ███    ███   ███    ███   ███    ███   ███    ███ ███▄▄▄███ \n" +
                "███       ███▌  ▄███▄▄▄██▀   ▄███▄▄▄▄██▀   ███    ███  ▄███▄▄▄▄██▀ ▀▀▀▀▀▀███ \n" +
                "███       ███▌ ▀▀███▀▀▀██▄  ▀▀███▀▀▀▀▀   ▀███████████ ▀▀███▀▀▀▀▀   ▄██   ███ \n" +
                "███       ███    ███    ██▄ ▀███████████   ███    ███ ▀███████████ ███   ███ \n" +
                "███▌    ▄ ███    ███    ███   ███    ███   ███    ███   ███    ███ ███   ███ \n" +
                "█████▄▄██ █▀   ▄█████████▀    ███    ███   ███    █▀    ███    ███  ▀█████▀  \n" +
                "                              ███    ███                ███    ███           \n");

        System.out.println("█████████████████████████████████████████████████████████████████████████████");

    };

    public static void menu() throws Exception {





        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        BookService bookService = new BookService();
        DemandeService demandeService = new DemandeService();
        while (true) {
            System.out.println("1. Add auteur");
            System.out.println("2. add Book");
            System.out.println("3. Modify book");
            System.out.println("4. delete book");
            System.out.println("5. search book");
            System.out.println("6. display book details");
            System.out.println("7. enprunt book");
            System.out.print("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

           


            switch (choice) {
                case 1:
                    adminService.addAuteur();
                case 2:
                 bookService.addBook();
                case 3:
                    bookService.updateBook();
                case 4:
                    bookService.deleteBook();
                case 5:
                    bookService.searchBook();
                case 6:
                    bookService.displayAllBooks();
                case 7:
                    demandeService.addDemande();
            }
        }

    }
}
