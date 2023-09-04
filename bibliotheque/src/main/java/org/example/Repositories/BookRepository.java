package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookRepository {

    Config config = new Config();

    public void Insert(Book book) {
        String insertQuery = "INSERT INTO book (title, quantitytotal, quantitydispo, bookmissing, prix, auteurid) VALUES (?, ?, ?, 0, ?, 1)";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getQuantitytotal());
            preparedStatement.setInt(3, book.getQuantitytotal());
            preparedStatement.setFloat(4, book.getPrix());

            //preparedStatement.setInt(5, book.getAuteur().getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully!");
            } else {
                System.out.println("Failed to add the book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void update(){}

    public void delete(){}


}
