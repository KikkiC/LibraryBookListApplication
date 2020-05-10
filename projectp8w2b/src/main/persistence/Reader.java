package persistence;

import model.Book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//cited from TellerApp
// A reader that can read book data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of books parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Book> readBooks(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of books parsed from list of strings
    // where each string contains data for one book
    private static List<Book> parseContent(List<String> fileContent) {
        List<Book> books = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            books.add(parseAccount(lineComponents));
        }

        return books;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // title of the book, element 1 represents the pagecount,
    // elements 2 represents the next barcode to be constructed,
    // element 3 represents the current barcode of the book,
    // element 4 represents whether the book is checked out or not
    // EFFECTS: returns a book constructed from components
    private static Book parseAccount(List<String> components) {
        String title = components.get(0);
        int pagecount = Integer.parseInt(components.get(1));
        int barcode = Integer.parseInt(components.get(2));
        boolean checkedout = Boolean.parseBoolean(components.get(3));
        return new Book(title, pagecount, barcode, checkedout);
    }
}
