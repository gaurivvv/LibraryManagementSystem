import java.util.*;

public class Library {
    ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
        System.out.println("Book added!");
    }

    public void viewBooks() {
        for (Book b : books) {
            b.displayBook();
        }
    }

    public void issueBook(int bookId) {
        for (Book b : books) {
            if (b.bookId == bookId && !b.isIssued) {
                b.isIssued = true;
                System.out.println("Book issued!");
                return;
            }
        }
        System.out.println("Book not available!");
    }

    public void returnBook(int bookId) {
        for (Book b : books) {
            if (b.bookId == bookId && b.isIssued) {
                b.isIssued = false;
                System.out.println("Book returned!");
                return;
            }
        }
        System.out.println("Book not found or not issued.");
    }
}
