package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Auteur;
import org.example.Entities.Book;
import org.example.Menu.MainMenu;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {
    Config config = new Config();

    public void Insert(Book book) throws SQLException {
        String insertQuery = "INSERT INTO book (title, quantitytotal, quantitydispo, bookmissing, prix, auteurid, isbn) VALUES (?, ?, ?, 0, ?, ?, ?)";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getQuantitytotal());
            preparedStatement.setInt(3, book.getQuantitytotal());
            preparedStatement.setFloat(4, book.getPrix());
            preparedStatement.setInt(5, book.getAuteur().getId());
            preparedStatement.setString(6, book.getIsbn());
            System.out.println(book.getIsbn());


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully!");
                MainMenu.menu();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Book findBook(int isbn1) throws Exception {
        String selectQuery = "SELECT * FROM book WHERE id = ?"; // Assuming 'id' is the ISBN in your database

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, isbn1);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                int id = data.getInt("id");
                String title = data.getString("title");
                int quantityTotal = data.getInt("quantitytotal");
                int quantityDispo = data.getInt("quantitydispo");
                int bookMissing = data.getInt("bookmissing");
                float prix = data.getFloat("prix");
                int auteurId = data.getInt("auteurid");
                String isbn = data.getString("isbn");

                // Retrieve the author from the AuteurRepository
                AuteurRepository auteurRepository = new AuteurRepository();
                Auteur author = auteurRepository.findById(auteurId);

                // Create a Book object with the retrieved data
                Book book = new Book(title, quantityTotal, quantityDispo, bookMissing, prix, author , isbn);
                book.setId(id);

                return book;
            }
        } catch (SQLException e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }

        return null; // Return null if no book with the given ISBN is found
    }


    public void update(Book book) throws SQLException {
        String updateQuery = "UPDATE book SET title = ?, quantitytotal = ?, quantitydispo = ?, prix = ?, auteurid = 5, isbn = ? WHERE id = ?";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getQuantitytotal());
            preparedStatement.setInt(3, book.getQuantitydispo());
            preparedStatement.setFloat(4, book.getPrix());
           // preparedStatement.setInt(5, book.getAuteur().getId());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.setString(5, book.getIsbn());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully!");
                MainMenu.menu();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(int bookId) throws SQLException {
        String deleteQuery = "DELETE FROM book WHERE id = ?";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book with ID " + bookId + " deleted successfully!");
                MainMenu.menu();
            } else {
                System.out.println("No book found with ID " + bookId + ". Nothing deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
