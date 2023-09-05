package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Auteur;
import org.example.Menu.MainMenu;

import java.sql.*;

public class AuteurRepository {

    Config config = new Config();
    public Auteur Insert(Auteur auteur){

        String insertQuery = "INSERT INTO auteur (name) VALUES (?)";
        // Initialize with a default value

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = config.createConnection().prepareStatement(insertQuery)) {
            preparedStatement.setString(1, auteur.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                Auteur auteur2 = findByName(auteur.getName());
                System.out.println("Person added successfully with ID: " + auteur2.getId()+auteur2.getName());
                return auteur2;
            } else {
                System.out.println("Failed to add person.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;

    }
    public Auteur findByName(String name) throws Exception {

        String findQuery = "select * from auteur where name='"+name+"'";
        try{
            PreparedStatement preparedStatement = config.createConnection().prepareStatement(findQuery);
            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                int id = data.getInt("id"); // Adjust this line based on your database schema
                // Retrieve other fields as needed

                Auteur auteur = new Auteur(id, name); // Create an Auteur object with retrieved data
                System.out.println(auteur.getId());
                return auteur;

            }else {
                Auteur auteur = new Auteur(name);
                AuteurRepository auteurRepository = new AuteurRepository();
                return auteurRepository.Insert(auteur);


            }
        } catch (SQLException e) {
            throw new Exception("error found :"+ e.getMessage());
        }

    }
}
