package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Auteur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuteurRepository {

    Config config = new Config();
    public void Insert(Auteur auteur){

        String insertQuery = "INSERT INTO auteur (name) VALUES (?)";
        try (PreparedStatement preparedStatement = config.createConnection().prepareStatement(insertQuery)) {
            preparedStatement.setString(1, auteur.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Person added successfully!");
            } else {
                System.out.println("Failed to add person.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
