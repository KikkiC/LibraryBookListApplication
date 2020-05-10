package persistence;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    @BeforeEach
    void runBefore() {
        Reader reader = new Reader();
    }

    @Test
    void testParseBooksFile1() {
        try {
            List<Book> books = Reader.readBooks(new File("./data/testBookFile1.txt"));
            Book book1 = books.get(0);
            assertEquals(1, book1.getBarcode());
            assertEquals("The Hunger Games", book1.getTitle());
            assertEquals(374, book1.getPagecount());
            assertTrue(book1.getCheckedOut());

            Book book2 = books.get(1);
            assertEquals(2, book2.getBarcode());
            assertEquals("Catching Fire", book2.getTitle());
            assertEquals(391, book2.getPagecount());
            assertTrue(book2.getCheckedOut());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2() {
        try {
            List<Book> books = Reader.readBooks(new File("./data/testBookFile2.txt"));
            Book book1 = books.get(0);
            assertEquals(40, book1.getBarcode());
            assertEquals("The Maze Runner", book1.getTitle());
            assertEquals(375, book1.getPagecount());
            assertTrue(book1.getCheckedOut());

            Book book2 = books.get(1);
            assertEquals(41, book2.getBarcode());
            assertEquals("The Scorch Trials", book2.getTitle());
            assertEquals(361, book2.getPagecount());
            assertTrue(book2.getCheckedOut());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readBooks(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
