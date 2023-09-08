package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Auteur;
import org.example.Entities.Users;
import org.example.Menu.MainMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    Config config = new Config();
    //Users users = new Users();
    //UserRepository userRepository = new UserRepository();
    public Users insert(Users user) {
        String insertQuery = "INSERT INTO users (fullname, email, phone) VALUES (?, ?, ?)";
        try { Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());

            int rowsAffected = preparedStatement.executeUpdate();

           // Users users = new Users();



            if (rowsAffected > 0) {
                Users users2 = findByPhone(user.getPhone());
                System.out.println("Auteur added successfully with ID: " + users2.getId());
           //     MainMenu.menu();
                return users2;
               // return user; // Return the user object with the updated ID
            } else {
                System.out.println("Failed to add user.");
                return null; // Return null to indicate failure
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Users findByPhone(String phone) {
        String findQuery = "SELECT * FROM users WHERE phone=?";
        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findQuery)) {

            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                return user;
            } else {

               // Users users = new Users(String fullname, String email, phone);
                // userRepository.insert();



               // Auteur auteur = new Auteur(name);
             //   AuteurRepository auteurRepository = new AuteurRepository();
               // return auteurRepository.Insert(auteur);

                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }}
