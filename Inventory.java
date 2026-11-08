import java.util.ArrayList;
import java.util.List;

/**
 * Inventory.java
 * Divine Chamoh
 * Keeps track of the books on the shelf (main inventory) and the books
 * that are currently borrowed (lending inventory).
 */
public class Inventory {

    private final ArrayList<Book> mainInventory = new ArrayList<>();
    private final ArrayList<Book> lendingInventory = new ArrayList<>();

    /**
     * Creates a new Book and adds it to the main inventory.
     * @return false, without adding, if a book with that id already exists
     */
    public boolean addBook(int id, String title, String author, String isbn, int numberOfPages) {
        if (findById(mainInventory, id) != null || findById(lendingInventory, id) != null) {
            return false;
        }
        mainInventory.add(new Book(id, title, author, isbn, numberOfPages));
        return true;
    }

    /** Moves a book from the main inventory to the lending inventory. */
    public boolean borrowBook(int id) {
        return move(id, mainInventory, lendingInventory);
    }

    /** Moves a book from the lending inventory back to the main inventory. */
    public boolean returnBook(int id) {
        return move(id, lendingInventory, mainInventory);
    }

    /** Prints every book in the main inventory, then how many are available. */
    public void printAll() {
        if (mainInventory.isEmpty()) {
            System.out.println("No books are currently available in the library.");
            return;
        }
        for (Book book : mainInventory) {
            book.printBookInfo();
        }
        System.out.println("Books available: " + getMainInventoryCount());
    }

    /** Returns every main-inventory book whose title contains the text, ignoring case. */
    public List<Book> searchByTitle(String title) {
        List<Book> matches = new ArrayList<>();
        for (Book book : mainInventory) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches.add(book);
            }
        }
        return matches;
    }

    public boolean isBorrowed(int id) {
        return findById(lendingInventory, id) != null;
    }

    public int getMainInventoryCount() {
        return mainInventory.size();
    }

    public int getLendingCount() {
        return lendingInventory.size();
    }

    /** Shared by borrowBook() and returnBook(): moves the book with this id between lists. */
    private boolean move(int id, List<Book> from, List<Book> to) {
        Book book = findById(from, id);
        if (book == null) {
            return false;
        }
        from.remove(book);
        to.add(book);
        return true;
    }

    private Book findById(List<Book> books, int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
