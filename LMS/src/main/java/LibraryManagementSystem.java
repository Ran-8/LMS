// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = Library.getInstance();

        // Adding branches
        LibraryBranch branchA = new LibraryBranch("Branch A");
        LibraryBranch branchB = new LibraryBranch("Branch B");
        library.addBranch(branchA);
        library.addBranch(branchB);

        // Adding books to branches
        branchA.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "1234567890", 1925));
        branchB.addBook(new Book("1984", "George Orwell", "1234567891", 1949));

        // Adding patrons
        Patron alice = new Patron("Alice", "P001");
        alice.addPreference("Gatsby");
        library.addPatron(alice);

        // Book transfer
        library.transferBook("1234567891", "Branch B", "Branch A");

        // Book reservation
        library.reserveBook("1234567890", "P001");

        // Show recommendations
        System.out.println("Recommendations for Alice: " + library.recommendBooks("P001"));

        // Notify reservation
        library.notifyReservation("1234567890");
    }
}