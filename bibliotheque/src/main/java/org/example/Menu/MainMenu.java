package org.example.Menu;

import org.example.Config.Config;
import org.example.Entities.Auteur;
import org.example.Services.AdminService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    public void menu(){



        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        while (true) {
            System.out.println("1. Add Person");
            System.out.println("2. Delete Person");
            System.out.println("3. Modify Person");
            System.out.println("4. Show List");
            System.out.println("5. title");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


            switch (choice) {
                case 1:
                    adminService.addAuteur();


                    break;


            }
        }

    }
}
