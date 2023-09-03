package org.example.Services;


import org.example.Entities.Auteur;
import org.example.Repositories.AuteurRepository;

import java.util.Scanner;

public class AdminService {

    Scanner scanner = new Scanner(System.in);
    AuteurRepository auteurRepository = new AuteurRepository();
    public void addAuteur(){

        Auteur auteur = new Auteur();

        System.out.print("Enter person's name: ");
        auteur.setName(scanner.nextLine());
        auteurRepository.Insert(auteur);


    }


}
