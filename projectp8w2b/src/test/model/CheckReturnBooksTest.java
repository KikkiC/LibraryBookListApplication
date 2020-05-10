package model;

import exceptions.NotCheckedOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckReturnBooksTest {
    CheckReturnBooks receipt;
    Book book1;
    Book book2;
    Book book3 = new Book("The Maze Runner", 375, true);

    @BeforeEach
    void runBefore() {
        receipt = new CheckReturnBooks();
        book1 = new Book("The Hunger Games", 379, false);
        book2 = new Book("Divergent", 487, false);
        book3.setCheckedOut();
    }

    @Test
    void testConstructor() {
        assertEquals(0, receipt.getNumCheckedOutBook());
    }

    @Test
    void testAddCheckedOutBook() {
        receipt.addCheckedOutBook(book1);
        assertEquals(1, receipt.getNumCheckedOutBook());
        receipt.addCheckedOutBook(book2);
        assertEquals(2, receipt.getNumCheckedOutBook());
        receipt.addCheckedOutBook(book3);
        assertTrue(book3.getCheckedOut());
        assertEquals(2, receipt.getNumCheckedOutBook());
    }

    @Test
    void testReturnCheckedOutBook() {
        receipt.addCheckedOutBook(book1);
        receipt.addCheckedOutBook(book2);
        assertEquals(2, receipt.getNumCheckedOutBook());
        try {
            receipt.returnCheckedOutBook(book2);
            assertEquals(1, receipt.getNumCheckedOutBook());
            assertFalse(book2.getCheckedOut());
            assertTrue(book3.getCheckedOut());
        } catch (NotCheckedOut e) {
            fail("NotCheckedOut should not have been thrown!");
        }
    }

    @Test
    void testFailReturnBook() {
        try {
            receipt.addCheckedOutBook(book1);
            receipt.returnCheckedOutBook(book1);
            receipt.returnCheckedOutBook(book2);
            fail("NotCheckedOut should have been thrown!");
        } catch (NotCheckedOut notCheckedOut) {
            // expected
        }
    }

    @Test
    void testGetTitle() {
        receipt.addCheckedOutBook(book1);
        receipt.addCheckedOutBook(book2);
        assertEquals("Divergent", receipt.returnBookTitle(1));
        assertEquals("The Hunger Games", receipt.returnBookTitle(0));
    }

    @Test
    void testGetPageCount() {
        receipt.addCheckedOutBook(book1);
        receipt.addCheckedOutBook(book2);
        assertEquals(379, receipt.returnPageCount(0));
        assertEquals(487, receipt.returnPageCount(1));
    }
}