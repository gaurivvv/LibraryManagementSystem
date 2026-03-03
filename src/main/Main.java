package main;

import model.Book;
import service.LibraryService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();

        while (true) {

            System.out.println("\n===== SMART LIBRARY SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    int id;
                    try {
                        System.out.print("Enter Book ID: ");
                        id = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid ID! Must be a number.");
                        scanner.nextLine();
                        break;
                    }

                    scanner.nextLine(); // clear buffer

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    Book book = new Book(id, title, author);
                    libraryService.addBook(book);
                    break;

                case 2:
                    libraryService.viewAllBooks();
                    break;

                case 3:
                    try {
                        System.out.print("Enter Book ID to issue: ");
                        int issueId = scanner.nextInt();
                        libraryService.issueBook(issueId);
                    } catch (Exception e) {
                        System.out.println("Invalid ID! Must be a number.");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Book ID to return: ");
                        int returnId = scanner.nextInt();
                        libraryService.returnBook(returnId);
                    } catch (Exception e) {
                        System.out.println("Invalid ID! Must be a number.");
                        scanner.nextLine();
                    }
                    break;

                case 5:
                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter title to search: ");
                    String keyword = scanner.nextLine();
                    libraryService.searchBookByTitle(keyword);
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}