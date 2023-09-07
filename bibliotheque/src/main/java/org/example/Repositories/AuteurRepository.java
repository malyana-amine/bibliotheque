package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Auteur;
import org.example.Menu.MainMenu;

import java.sql.*;

public class AuteurRepository {

    Config config = new Config();
    public Auteur Insert(Auteur auteur){

        String insertQuery = "INSERT INTO auteur (name) VALUES (?)";


        try (
             PreparedStatement preparedStatement = config.createConnection().prepareStatement(insertQuery)) {
            preparedStatement.setString(1, auteur.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                Auteur auteur2 = findByName(auteur.getName());
                System.out.println("Auteur added successfully with ID: " + auteur2.getId()+auteur2.getName());
                MainMenu.menu();
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
                int id = data.getInt("id");

                Auteur auteur = new Auteur(id, name);
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
    public Auteur findById(int id) throws Exception {
        String findQuery = "SELECT * FROM auteur WHERE id = ?";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findQuery)) {
            preparedStatement.setInt(1, id);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                String name = data.getString("name");

                Auteur auteur = new Auteur(id, name);
                return auteur;
            } else {

                throw new Exception("Author not found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }
    }

}
