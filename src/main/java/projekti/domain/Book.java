package projekti.domain;

import projekti.util.Check;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Object that represents a book.
 *
 * @author Rsl1122
 */
public class Book extends AbstractPropertyStore {

    /**
     * Class that lists properties of a Book object.
     * <p>
     * Use this to access properties {@code book.getProperty(Properties.NAME)}
     */
    public static class Properties {

        public static final Property<String> AUTHOR = new Property<>("AUTHOR", String.class, author -> author != null && !author.isEmpty());
        public static final Property<String> TITLE = new Property<>("NAME", String.class, title -> title != null && !title.isEmpty());
        public static final Property<String> ISBN = new Property<>("ISBN", String.class, isbn -> isbn != null && !isbn.isEmpty());
        public static final Property<String> DESCRIPTION = new Property<>("DESCRIPTION", String.class);
        public static final Property<Integer> ID = CommonProperties.ID;

        public static List<Property> getAll() {
            List<Property> properties = new ArrayList<>();
            for (Field field : Properties.class.getDeclaredFields()) {
                if (!Modifier.isPublic(field.getModifiers())) {
                    continue;
                }
                try {
                    properties.add((Property) field.get(null));
                } catch (IllegalAccessException ignored) {
                    /* Inaccessible field */
                }
            }
            return properties;
        }
    }

    @Override
    public List<Property> getProperties() {
        return Properties.getAll();
    }

    private final String type;

    /**
     * Create a new book.
     *
     * @param author Author of the book, not null or empty.
     * @param title Title of the book, not null or empty.
     * @param isbn ISBN of the book, not null or empty.
     * @param description Description of the book, can be empty
     */
    public Book(String author, String title, String isbn, String description) {
        Check.notNull(author, () -> new IllegalArgumentException("Author should not be null"));
        Check.notNull(title, () -> new IllegalArgumentException("Title should not be null"));
        Check.notNull(isbn, () -> new IllegalArgumentException("ISBN should not be null"));

        Check.isFalse(author.isEmpty(), () -> new IllegalArgumentException("Author should not be empty"));
        Check.isFalse(title.isEmpty(), () -> new IllegalArgumentException("Title should not be empty"));
        Check.isFalse(isbn.isEmpty(), () -> new IllegalArgumentException("ISBN should not be empty"));

        addProperty(Properties.AUTHOR, author);
        addProperty(Properties.TITLE, title);
        addProperty(Properties.ISBN, isbn);
        addProperty(Properties.DESCRIPTION, description);

        this.type = "BOOK";
    }
    
    public Book(String author, String title, String isbn) {
        this(author, title, isbn, "");
    }

    public void setID(Integer id) {
        addProperty(Properties.ID, id);
    }

    public void setDescription(String description) {
        addProperty(Properties.DESCRIPTION, description);
    }

    /**
     * Get the database ID of the book.
     *
     * @return database ID of the book.
     * @deprecated Use {@code book.getProperty(Properties.ID)} instead.
     */
    @Deprecated
    public Integer getID() {
        return getProperty(Properties.ID).orElse(null);
    }

    /**
     * Get the author of the book.
     *
     * @return Author of the book.
     * @deprecated Use {@code book.getProperty(Properties.AUTHOR)} instead.
     */
    @Deprecated
    public String getAuthor() {
        return getProperty(Properties.AUTHOR).orElse(null);
    }

    /**
     * Get the title of the book.
     *
     * @return Title of the book.
     * @deprecated Use {@code book.getProperty(Properties.TITLE)} instead.
     */
    @Deprecated
    public String getTitle() {
        return getProperty(Properties.TITLE).orElse(null);
    }

    /**
     * Get the ISBN of the book.
     *
     * @return ISBN of the book.
     * @deprecated Use {@code book.getProperty(Properties.ISBN)} instead.
     */
    @Deprecated
    public String getISBN() {
        return getProperty(Properties.ISBN).orElse(null);
    }

    public String getType() {
        return type;
    }

    @Deprecated
    public String getDescription() {
        return getProperty(Properties.DESCRIPTION).orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(type, book.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type);
    }

}
