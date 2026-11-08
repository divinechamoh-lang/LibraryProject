/**
 * Book.java
 * Divine Chamoh
 * A library book with a unique id, title, author, ISBN number, and page count.
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private String isbn;
    private int numberOfPages;

    /** Default constructor: creates a book with empty text fields and zero numbers. */
    public Book() {
        this(0, "", "", "", 0);
    }

    public Book(int id, String title, String author, String isbn, int numberOfPages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /** Prints this book's information on one line. */
    public void printBookInfo() {
        System.out.printf("ID: %d | Title: %s | Author: %s | ISBN: %s | Pages: %d%n",
                id, title, author, isbn, numberOfPages);
    }
}
