import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

public // Library class with multi-branch, reservations, and recommendations
class Library {
    private static Library instance;
    private Map<String, LibraryBranch> branches = new HashMap<>();
    private Map<String, Patron> patrons = new HashMap<>();
    private Map<String, Queue<String>> reservations = new HashMap<>();

    private Library() {}

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Branch Management
    public void addBranch(LibraryBranch branch) {
        branches.put(branch.getBranchName(), branch);
    }

    public LibraryBranch getBranch(String branchName) {
        return branches.get(branchName);
    }

    // Book Transfer
    public boolean transferBook(String isbn, String fromBranchName, String toBranchName) {
        LibraryBranch fromBranch = branches.get(fromBranchName);
        LibraryBranch toBranch = branches.get(toBranchName);

        if (fromBranch != null && toBranch != null) {
            Book book = fromBranch.getBook(isbn);
            if (book != null && !book.isBorrowed()) {
                fromBranch.removeBook(isbn);
                toBranch.addBook(book);
                return true;
            }
        }
        return false;
    }

    // Patron Management
    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
    }

    public Patron getPatron(String id) {
        return patrons.get(id);
    }

    // Reservation System
    public void reserveBook(String isbn, String patronId) {
        reservations.putIfAbsent(isbn, new LinkedList<>());
        reservations.get(isbn).add(patronId);
    }

    public void notifyReservation(String isbn) {
        if (reservations.containsKey(isbn) && !reservations.get(isbn).isEmpty()) {
            String nextPatronId = reservations.get(isbn).poll();
            System.out.println("Notification sent to Patron ID: " + nextPatronId + " for book ISBN: " + isbn);
        }
    }

    // Recommendation System
    public List<Book> recommendBooks(String patronId) {
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            return Collections.emptyList();
        }

        List<Book> recommendations = new ArrayList<>();
        for (String preference : patron.getPreferences()) {
            for (LibraryBranch branch : branches.values()) {
                for (Book book : branch.getInventory().values()) {
                    if (book.getTitle().contains(preference) || book.getAuthor().contains(preference)) {
                        recommendations.add(book);
                    }
                }
            }
        }
        return recommendations;
    }
}