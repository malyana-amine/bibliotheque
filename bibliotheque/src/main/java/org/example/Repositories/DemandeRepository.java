package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Demande;
import org.example.Menu.MainMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DemandeRepository {

    Config config = new Config();
    public void InsertDemande(Demande demande) throws SQLException {
        String insertQuery = "INSERT INTO demande (userid, bookid, startdate, enddate, quantity, returned) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, demande.getUser().getId());
            preparedStatement.setInt(2, demande.getBook().getId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(demande.getStartdate()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(demande.getEnddate()));
            preparedStatement.setInt(5, demande.getQuantity());
            preparedStatement.setBoolean(6, demande.isReturned());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Demande added successfully!");
                MainMenu.menu();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}


