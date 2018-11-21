package projekti.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class BookTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void canCreateBookWithAllParameters() {
        new Book("Matti Meikäläinen", "Esimerkki-ihmisen arkipäivä", "1323213");
    }

    @Test
    public void canNotCreateBookWithoutAuthor() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Author should not be null");
        new Book(null, "Esimerkki-ihmisen arkipäivä", "1323213");
    }

    @Test
    public void canNotCreateBookWithoutAuthorName() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Author should not be empty");
        new Book("", "Esimerkki-ihmisen arkipäivä", "1323213");
    }

    @Test
    public void canNotCreateBookWithoutTitle() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Title should not be null");
        new Book("Matti Meikäläinen",  null, "1323213");
    }

    @Test
    public void canNotCreateBookWithEmptyTitle() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Title should not be empty");
        new Book("Matti Meikäläinen", "", "1323213");
    }

    @Test
    public void canNotCreateBookWithoutISBN() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("ISBN should not be null");
        new Book("Matti Meikäläinen",  "Esimerkki-ihmisen arkipäivä", null);
    }

    @Test
    public void canNotCreateBookWithEmptyISBN() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("ISBN should not be empty");
        new Book("Matti Meikäläinen", "Esimerkki-ihmisen arkipäivä", "");
    }

}