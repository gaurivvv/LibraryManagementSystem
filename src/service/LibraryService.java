package service;

import model.Book;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class LibraryService {

    private HashMap<Integer, Book> books;
    private final String FILE_NAME = "books.txt";

    public LibraryService() {
        books = new HashMap<>();
        loadFromFile();
    }

    // Add Book
    public void addBook(Book book) {

        if (books.containsKey(book.getId())) {
            System.out.println("Book with this ID already exists!");
            return;
        }

        books.put(book.getId(), book);
        saveToFile();
        System.out.println("Book added successfully!");
    }

    // View All Books
    public void viewAllBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    // Issue Book
    public void issueBook(int id) {

        Book book = books.get(id);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isIssued()) {
            book.setIssued(true);
            saveToFile();
            System.out.println("Book issued successfully!");
        } else {
            System.out.println("Book already issued.");
        }
    }

    // Return Book
    public void returnBook(int id) {

        Book book = books.get(id);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.isIssued()) {
            book.setIssued(false);
            saveToFile();
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book was not issued.");
        }
    }

    // Search Book
    public void searchBookByTitle(String keyword) {

        boolean found = false;

        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    // Save Data to File
    private void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Book book : books.values()) {
                writer.write(book.getId() + "," +
                        book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.isIssued());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Load Data from File
    private void loadFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                boolean issued = Boolean.parseBoolean(parts[3]);

                Book book = new Book(id, title, author);
                book.setIssued(issued);

                books.put(id, book);
            }

        } catch (IOException e) {
            // File may not exist first time
        }
    }
}