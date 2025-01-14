import java.util.*;

class Patron {
    private String name;
    private String id;
    private List<Book> borrowedBooks;
    private List<String> preferences;

    public Patron(String name, String id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
        this.preferences = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrowBook();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public void addPreference(String preference) {
        preferences.add(preference);
    }

    public List<String> getPreferences() {
        return preferences;
    }

    @Override
    public String toString() {
        return String.format("Patron[Name='%s', ID='%s', BorrowedBooks=%d, Preferences=%s]",
                name, id, borrowedBooks.size(), preferences);
    }
}