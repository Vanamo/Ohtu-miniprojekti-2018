package projekti.domain;

import projekti.util.Check;

/**
 * Object that represents a book.
 *
 * @author Rsl1122
 */
public class Book {

    private final String author;
    private final String title;
    private final String isbn;
    private final String type;

    /**
     * Create a new book.
     *
     * @param author Author of the book, not null or empty.
     * @param title  Title of the book, not null or empty.
     * @param isbn   ISBN of the book, not null or empty.
     */
    public Book(String author, String title, String isbn) {
        Check.notNull(author, () -> new IllegalArgumentException("Author should not be null"));
        Check.notNull(title, () -> new IllegalArgumentException("Title should not be null"));
        Check.notNull(isbn, () -> new IllegalArgumentException("isbn should not be null"));

        Check.isFalse(author.isEmpty(), () -> new IllegalArgumentException("Author should not be empty"));
        Check.isFalse(title.isEmpty(), () -> new IllegalArgumentException("Title should not be empty"));
        Check.isFalse(isbn.isEmpty(), () -> new IllegalArgumentException("isbn should not be empty"));

        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.type = "BOOK";
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return isbn;
    }

    public String getType() {
        return type;
    }
}
