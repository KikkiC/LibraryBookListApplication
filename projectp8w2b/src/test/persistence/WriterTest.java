package persistence;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private static final String TEST_FILE = "./data/testBooks.txt";
    private Writer testWriter;
    private Book book1;
    private Book book2;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        book1 = new Book("The Maze Runner", 375, 1, true);
        book2 = new Book("The Scorch Trials", 361, 2, true);
    }

    @Test
    void testWriteBooks() {
        // save books to file
        testWriter.write(book1);
        testWriter.write(book2);
        testWriter.close();

        // now read them back in and verify that the books have the expected values
        try {
            List<Book> books = Reader.readBooks(new File(TEST_FILE));
            Book book1 = books.get(0);
            assertEquals(1, book1.getBarcode());
            assertEquals("The Maze Runner", book1.getTitle());
            assertEquals(375, book1.getPagecount());
            assertTrue(book1.getCheckedOut());

            Book book2 = books.get(1);
            assertEquals(2, book2.getBarcode());
            assertEquals("The Scorch Trials", book2.getTitle());
            assertEquals(361, book2.getPagecount());
            assertTrue(book2.getCheckedOut());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
