package org.example.Repositories;

import org.example.Config.Config;
import org.example.Entities.Auteur;
import org.example.Entities.Book;
import org.example.Menu.MainMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class BookRepository {
    Config config = new Config();

    public void Insert(Book book) {
        String insertQuery = "INSERT INTO book (title, quantitytotal, quantitydispo, bookmissing, prix, auteurid, isbn) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = config.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getQuantitytotal());
            preparedStatement.setInt(3, book.getQuantitytotal());
            preparedStatement.setInt(4, book.getQuantitytotal()-book.getQuantitytotal());
            preparedStatement.setFloat(5, book.getPrix());
            preparedStatement.setInt(6, book.getAuteur().getId());
            preparedStatement.setString(7, book.getIsbn());



            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully!");
                MainMenu.menu();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public Book findBook(int isbn1) throws Exception {
        String selectQuery = "SELECT * FROM book WHERE id = ?";

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


                AuteurRepository auteurRepository = new AuteurRepository();
                Auteur author = auteurRepository.findById(auteurId);


                Book book = new Book(title, quantityTotal, quantityDispo, bookMissing, prix, author , isbn);
                book.setId(id);

                return book;
            }
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }

        return null;
    }


    public void update(Book book)  {
        String updateQuery = "UPDATE book SET title = ?, quantitytotal = ?, quantitydispo = ?, prix = ?, isbn = ? WHERE id = ?";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getQuantitytotal());
            preparedStatement.setInt(3, book.getQuantitydispo());
            preparedStatement.setFloat(4, book.getPrix());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.setString(5, book.getIsbn());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully!");
                MainMenu.menu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delete(int bookId)  {
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
                MainMenu.menu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() throws Exception {
        List<Book> allBooks = new ArrayList<>();

        String selectQuery = "SELECT * FROM book";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                int id = data.getInt("id");
                String title = data.getString("title");
                int quantityTotal = data.getInt("quantitytotal");
                int quantityDispo = data.getInt("quantitydispo");
                int bookMissing = data.getInt("bookmissing");
                float prix = data.getFloat("prix");
                int auteurId = data.getInt("auteurid");
                String isbn = data.getString("isbn");


                AuteurRepository auteurRepository = new AuteurRepository();
                Auteur author = auteurRepository.findById(auteurId);


                Book book = new Book(title, quantityTotal, quantityDispo, bookMissing, prix, author, isbn);
                book.setId(id);

                allBooks.add(book);
            }
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }

        return allBooks;
    }


    public List<Book> searchBook(String searchTerm) throws Exception {
        List<Book> foundBooks =new ArrayList<Book>();
        String searchQuery = "SELECT * FROM book WHERE title LIKE ? OR id = ? OR auteurid = ?";

        try (Connection connection = config.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");

            try {
                int searchTermAsInt = Integer.parseInt(searchTerm);
                preparedStatement.setInt(2, searchTermAsInt); //
                preparedStatement.setInt(3, searchTermAsInt); //
            } catch (Exception e) {
                e.getMessage();
            }

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                int id = data.getInt("id");
                String title = data.getString("title");
                int quantityTotal = data.getInt("quantitytotal");
                int quantityDispo = data.getInt("quantitydispo");
                int bookMissing = data.getInt("bookmissing");
                float prix = data.getFloat("prix");
                int auteurId = data.getInt("auteurid");
                String isbn = data.getString("isbn");


                AuteurRepository auteurRepository = new AuteurRepository();
                Auteur author = auteurRepository.findById(auteurId);


                Book book = new Book(title, quantityTotal, quantityDispo, bookMissing, prix, author, isbn);
                book.setId(id);

                foundBooks.add(book);

            }
        } catch (SQLException e) {
            throw new Exception("Error occurred: " + e.getMessage());
        }

        return foundBooks;
    }
    public void updateQuantityDispo(int bookId, int quantity) throws SQLException {
        String updateQuery = "UPDATE book SET quantitydispo = quantitydispo + ? WHERE id = ?";

        try (Connection connection = config.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quantity updated successfully!");
            } else {
                System.out.println("Failed to update quantity. Book not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
