public void issueBook(int id) {
    for (Book b : books) {
        if (b.getId() == id && !b.isIssued()) {
            b.issue();
            System.out.println("Book issued successfully.");
            return;
        }
    }
    System.out.println("Book not available or already issued.");
}

public void returnBook(int id) {
    for (Book b : books) {
        if (b.getId() == id && b.isIssued()) {
            b.returnBook();
            System.out.println("Book returned successfully.");
            return;
        }
    }
    System.out.println("Book not found or already returned.");
}
