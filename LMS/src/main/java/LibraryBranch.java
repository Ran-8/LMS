import java.util.HashMap;
import java.util.Map;

public class LibraryBranch {
    private String branchName;
    private Map<String, Book> inventory = new HashMap<>();

    public LibraryBranch(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

    public Map<String, Book> getInventory() {
        return inventory;
    }

    public void addBook(Book book) {
        inventory.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn) {
        inventory.remove(isbn);
    }

    public Book getBook(String isbn) {
        return inventory.get(isbn);
    }

    @Override
    public String toString() {
        return String.format("Branch[Name='%s', Books=%d]", branchName, inventory.size());
    }
}