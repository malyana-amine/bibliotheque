package org.example.Services;

import org.example.Entities.Demande;
import org.example.Entities.Users;
import org.example.Repositories.BookRepository;
import org.example.Repositories.UserRepository;

import java.util.Scanner;

public class DemandeService {

    Demande demande = new Demande();
    Scanner scanner = new Scanner(System.in);
    public void addDemande(){



        System.out.println("add ur phone :");
        String phone = scanner.nextLine();

        UserRepository userRepository = new UserRepository();
        if(userRepository.findByPhone(phone)==null){
            Users users = new Users();
            users.setPhone(phone);

            System.out.println("add ur full name: ");
            users.setFullname(scanner.nextLine());

            System.out.println("add ur email: ");
            users.setEmail(scanner.nextLine());

            userRepository.insert(users);
        }
        System.out.println("add ur book id: ");





    }

}
