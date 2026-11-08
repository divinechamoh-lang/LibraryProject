import java.util.List;
import java.util.Scanner;

/**
 * LibraryApp.java
 * Divine Chamoh
 * Menu-driven program for a basic library system: add, borrow, and return
 * books, search by title, and print the available books.
 */
public class LibraryApp {

    private static final Scanner INPUT = new Scanner(System.in);
    private static final Inventory LIBRARY = new Inventory();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            try {
                running = handleChoice(readInt("Enter your choice: "));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number. The operation was cancelled.");
            }
        }
        System.out.println("Exiting the program. Goodbye!");
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Add Book     3. Return Book      5. Print All Books");
        System.out.println("2. Borrow Book  4. Search by Title  6. Exit");
    }

    /** Runs the chosen menu option. Returns false when the user chooses Exit. */
    private static boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                searchByTitle();
                break;
            case 5:
                LIBRARY.printAll();
                break;
            case 6:
                return false;
            default:
                System.out.println("Invalid option. Please choose a number from 1 to 6.");
        }
        return true;
    }

    private static void addBook() {
        int id = readInt("Enter book id: ");
        String title = readText("Enter title: ");
        String author = readText("Enter author: ");
        String isbn = readText("Enter ISBN number: ");
        int numberOfPages = readInt("Enter number of pages: ");
        System.out.println(LIBRARY.addBook(id, title, author, isbn, numberOfPages)
                ? "Book added to the library."
                : "A book with that id already exists. Book not added.");
    }

    private static void borrowBook() {
        int id = readInt("Enter the id of the book to borrow: ");
        if (LIBRARY.borrowBook(id)) {
            System.out.println("Book successfully borrowed.");
        } else if (LIBRARY.isBorrowed(id)) {
            System.out.println("That book is already borrowed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void returnBook() {
        int id = readInt("Enter the id of the book to return: ");
        if (LIBRARY.returnBook(id)) {
            System.out.println("Book successfully returned.");
        } else if (LIBRARY.getLendingCount() == 0) {
            System.out.println("No books are currently borrowed.");
        } else {
            System.out.println("That book is not in the lending list.");
        }
    }

    private static void searchByTitle() {
        List<Book> matches = LIBRARY.searchByTitle(readText("Enter a full or partial title: "));
        if (matches.isEmpty()) {
            System.out.println("No matching book found.");
        }
        for (Book book : matches) {
            book.printBookInfo();
        }
    }

    /** Reads a whole number. Throws NumberFormatException, which main() catches, if the text is not one. */
    private static int readInt(String prompt) {
        return Integer.parseInt(readText(prompt));
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return INPUT.nextLine().trim();
    }
}
