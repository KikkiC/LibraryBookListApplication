package model;

import exceptions.NotCheckedOut;

import java.util.ArrayList;

// Represents a receipt that shows list of books borrowed
public class CheckReturnBooks {
    ArrayList<Book> receipt;

    // EFFECTS: constructs an empty list of books borrowed
    public CheckReturnBooks() {
        receipt = new ArrayList<>();
    }

    // EFFECTS: returns number of books borrowed
    public int getNumCheckedOutBook() {
        return receipt.size();
    }

    // MODIFES: this
    // EFFECTS: adds checked out books to list of books borrowed
    public void addCheckedOutBook(Book book) {
        if (!book.getCheckedOut()) {
            book.setCheckedOut();
            receipt.add(book);
        }
    }

    // REQUIRES: the list of books borrowed isn't empty
    // MODIFIES: this
    // EFFECTS: returns/removes book from list of books borrowed
    public void returnCheckedOutBook(Book book) throws NotCheckedOut {
        if (receipt.contains(book)) {
            book.setReturned();
            receipt.remove(book);
        } else {
            throw new NotCheckedOut();
        }
    }


    // EFFECTS: returns the title of the book at given index
    public String returnBookTitle(int index) {
        return receipt.get(index).getTitle();
    }

    // EFFECTS: returns the page count of the book at given index
    public int returnPageCount(int index) {
        return receipt.get(index).getPagecount();
    }
}