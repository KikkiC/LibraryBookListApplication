package model;

import java.io.PrintWriter;
import persistence.Saveable;
import persistence.Reader;

// Represents a book having a title and page count
public class Book implements Saveable {
    private int barcode;
    private boolean checkedout;
    private String title;
    private int pagecount;

    // EFFECTS: constructs a book with title, pagecount, and not checked out
    public Book(String title, int pagecount, boolean checkedout) {
        this.title = title;
        this.pagecount = pagecount;
        this.checkedout = false;
    }

    // EFFECTS: constructs a book with title, pagecount, next barcode, current barcode,
    // and boolean checked out.
    // NOTE: this constructor is to be used only when constructing an account from data
    // stored in file
    public Book(String title, int pagecount, int barcode, boolean checkedout) {
        this.title = title;
        this.pagecount = pagecount;
        this.barcode = barcode;
        this.checkedout = true;
    }

    // EFFECTS: returns title of this book
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns page count of this book
    public int getPagecount() {
        return pagecount;
    }


    // EFFECTS: returns the barcode of this book
    public int getBarcode() {
        return barcode;
    }

    // EFFECTS: returns true if book is checked out; false otherwise
    public boolean getCheckedOut() {
        return checkedout;
    }

    // MODIFIES: this
    // EFFECTS: sets status of book to isCheckedOut
    public void setCheckedOut() {
        checkedout = true;
    }

    // MODIFIES: this
    // EFFECTS: sets status of book to returned
    public void setReturned() {
        checkedout = false;
    }

    // EFFECTS: returns a string representation of book
//    public String toString() {
//        return "[ title = " + title + ", pagecount = " + pagecount + ", barcode = "
//                + barcode + ", checkedout = " + checkedout + "]";
//    }

    public void save(PrintWriter printWriter) {
        printWriter.print(title);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(pagecount);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(barcode);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(checkedout);
        printWriter.print(Reader.DELIMITER);
    }
}
