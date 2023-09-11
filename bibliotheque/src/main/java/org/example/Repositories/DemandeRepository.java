package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Book;
import org.example.Entities.Demande;
import org.example.Entities.Users;
import org.example.Menu.MainMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DemandeRepository {

    Config config = new Config();
    public void InsertDemande(Demande demande) throws SQLException {
        String insertQuery = "INSERT INTO demande (userid, bookid, startdate, enddate, quantity, returned) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, demande.getUser().getId());
            System.out.println(demande.getUser().getId());
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
            System.out.println(e.getMessage());
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void markDemandeAsReturned(int demandeId) throws SQLException {

        String updateQuery = "UPDATE demande SET returned = true, enddate = ? WHERE id = ?";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now())); // Set the return date as the current date
            preparedStatement.setInt(2, demandeId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Failed to mark as returned. Demande not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        }
        int bookId = getBookIdForDemande(demandeId); // Implement a method to get the book ID for the Demande
        int quantityReturned = getQuantityForDemande(demandeId); // Implement a method to get the quantity from the Demande

        BookRepository bookRepository = new BookRepository();
        bookRepository.updateQuantityDispo(bookId, quantityReturned);
    }

    public int getBookIdForDemande(int demandeId) throws SQLException {
        String selectQuery = "SELECT bookid FROM demande WHERE id = ?";
        int bookId = -1; // Initialize with an invalid value

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, demandeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bookId = resultSet.getInt("bookid");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        }

        return bookId;
    }


    public int getQuantityForDemande(int demandeId) throws SQLException {
        String selectQuery = "SELECT quantity FROM demande WHERE id = ?";
        int quantity = -1; // Initialize with an invalid value

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, demandeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        }

        return quantity;
    }
    public List<Demande> getAllDemande() {
        List<Demande> demandeList = new ArrayList<>();

        String selectQuery = "SELECT d.id, d.startdate, d.enddate, d.quantity, d.returned, u.id AS user_id, u.fullName, b.id AS book_id, b.title " +
                "FROM demande d " +
                "INNER JOIN users u ON d.userid = u.id " +
                "INNER JOIN book b ON d.bookid = b.id";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Demande demande = new Demande();
                demande.setId(resultSet.getInt("id"));
                demande.setStartdate(resultSet.getDate("startdate").toLocalDate());
                demande.setEnddate(resultSet.getDate("enddate").toLocalDate());
                demande.setQuantity(resultSet.getInt("quantity"));
                demande.setReturned(resultSet.getBoolean("returned"));

                Users user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setFullname(resultSet.getString("fullname"));
                demande.setUser(user);

                Book book = new Book();
                book.setId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                demande.setBook(book);

                demandeList.add(demande);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception
        }

        return demandeList;
    }






}


