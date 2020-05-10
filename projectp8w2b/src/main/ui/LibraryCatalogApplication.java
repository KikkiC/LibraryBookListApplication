package ui;

import model.Book;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

// Library Application using GUI Swing
public class LibraryCatalogApplication extends JFrame {
    private static final String BOOKS_FILE = "./data/books.txt";
    private ArrayList<Book> list;
    private int barcode = 1;
    private JFrame frame = new JFrame("Library Catalog Application");
    private JLabel bookTitle = new JLabel("Title: ");
    private JLabel bookPagecount = new JLabel("Pagecount: ");
    private JTextField title = new JTextField(20);
    private JTextField pagecount = new JTextField(20);
    private JButton borrowBtn = new JButton("Borrow");
    private JButton returnBtn = new JButton("Return");
    private JButton saveBtn = new JButton("Save");
    private JPanel infoArea = new JPanel();
    private JTable table = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    private PlaySound soundEffect = new PlaySound();
    private AddLabelsTextFields labelsTextFields = new AddLabelsTextFields();

    public LibraryCatalogApplication() {
        initializeGraphics();
        loadBooks();
        borrowBook();
        returnBook();
        saveBooks();
        mouseListener();
    }

    // initialize frame properties
    public void initializeGraphics() {
        frame.setLayout(new BorderLayout());
        createTable();
        addLabelTextField();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(700, 400);
    }

    // create table to appear in JFrame
    public void createTable() {
        Object[] columns = {"Book Title", "Pagecount", "Barcode"};
        model.setColumnIdentifiers(columns);
        model.setColumnCount(3);

        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", Font.PLAIN, 12);
        table.setFont(font);
        table.setRowHeight(20);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 500, 200);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // collect information from textfields and adds new entry to table with book title, pagecount, and barcode
    public void borrowBook() {
        Object[] row = new Object[5];
        borrowBtn.addActionListener(e -> {
            row[0] = title.getText();
            row[1] = Integer.parseInt(pagecount.getText());
            row[2] = barcode++;
            model.addRow(row);
            playSound();
        });
    }

    // select a book from the table and remove book from table
    public void returnBook() {
        returnBtn.addActionListener(e -> {
            int i = table.getSelectedRow();
            if (i >= 0) {
                model.removeRow(i);
            } else {
                System.out.println("Return Error. Please try again.");
            }
        });
    }

    // read from books.txt file and adds books to the table
    // if books.txt does not contain any books when saved, create new book list
    public void loadBooks() {
        try {
            List<Book> books = Reader.readBooks(new File(BOOKS_FILE));
            list = new ArrayList<>();
            list.addAll(books);
            model.setRowCount(list.size());
            for (int row = 0; row < table.getRowCount(); row++) {
                if (row < list.size()) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        if (col == 0) {
                            table.setValueAt(list.get(row).getTitle(), row, col);
                        } else if (col == 1) {
                            table.setValueAt(list.get(row).getPagecount(), row, col);
                        } else if (col == 2) {
                            table.setValueAt(list.get(row).getBarcode(), row, col);
                            barcode = list.get(row).getBarcode() + 1;
                        }
                    }
                }
            }
        } catch (IOException e) {
            list = new ArrayList<>();
        }
    }

    // save books to books.txt
    public void saveBooks() {
        saveBtn.addActionListener(e -> saveBooksFromTable());
    }

    // collect data from table and writes saved book information to books.txt
    public void saveBooksFromTable() {
        Writer writer = null;
        try {
            writer = new Writer(new File(BOOKS_FILE));
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        convertToBooks();
        for (Book book : list) {
            assert writer != null;
            writer.write(book);
        }
        assert writer != null;
        writer.close();
        System.out.println("Books saved to file " + BOOKS_FILE);
    }

    // converts data from books.txt to a list of books
    public void convertToBooks() {
        list = new ArrayList<>();
        for (int row = 0; row < table.getRowCount(); row++) {
            ArrayList<Object> objects = new ArrayList<>();
            for (int col = 0; col < table.getColumnCount(); col++) {
                objects.add(table.getValueAt(row, col));
                if (objects.size() == 3) {
                    String title = objects.get(0).toString();
                    int page = (int) objects.get(1);
                    int code = (int) objects.get(2);
                    Book book = new Book(title, page, code, true);
                    list.add(book);
                }
            }
        }
    }

    // lets out doorbell sound when "Borrow" button is pressed
    public void playSound() {
        soundEffect.playSound();
    }

    // allows user to click table data
    public void mouseListener() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                title.setText(model.getValueAt(i, 0).toString());
                pagecount.setText(model.getValueAt(i, 1).toString());
            }
        });
    }

    // initializes JLabels and JTextFields
    public void addLabelTextField() {
        infoArea.setLayout(new GridLayout(0, 1));
        infoArea.setSize(new Dimension(0,0));

        infoArea.add(labelsTextFields.createLabelTextFields(bookTitle, title));

        infoArea.add(labelsTextFields.createLabelTextFields(bookPagecount, pagecount));

        addButtons();
        frame.add(infoArea, BorderLayout.SOUTH);
    }

    // initializes JButtons
    public void addButtons() {
        JPanel buttonArea = new JPanel();

        buttonArea.add(borrowBtn, BorderLayout.LINE_START);
        buttonArea.add(returnBtn);
        buttonArea.add(saveBtn, BorderLayout.LINE_END);
        infoArea.add(buttonArea);
    }

    public static void main(String[] args) {
        new LibraryCatalogApplication();
    }
}