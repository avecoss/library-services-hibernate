package dev.alexcoss.dao;

import dev.alexcoss.models.Book;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookDAOTest {

    /*private static final String CREATE_TABLE_SQL = "CREATE TABLE book\n" +
        "(\n" +
        "    id        SERIAL PRIMARY KEY,\n" +
        "    person_id INT,\n" +
        "    name      VARCHAR(255) NOT NULL,\n" +
        "    author    VARCHAR(100) NOT NULL,\n" +
        "    year      INTEGER\n" +
        ")";
    private static final String H2_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    private BookDAO bookDAO;
    private JdbcDataSource dataSource;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = new JdbcDataSource();
        dataSource.setURL(H2_URL);

        bookDAO = new BookDAO(new JdbcTemplate(dataSource));

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_SQL)) {
            preparedStatement.execute();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE book")) {
            preparedStatement.execute();
        }
    }

    @Test
    void shouldAddItem() {
        Book book = new Book(1, "TestBook", "TestAuthor", 2022);
        bookDAO.addItem(book);

        List<Book> retrievedBooks = bookDAO.index();

        assertNotNull(retrievedBooks);
        assertEquals(1, retrievedBooks.size());
        assertEquals(book, retrievedBooks.get(0));
    }

    @Test
    void shouldGetAllItems() {
        Book book1 = new Book(1, "Book1", "Author1", 2000);
        Book book2 = new Book(2, "Book2", "Author2", 2010);
        bookDAO.addItem(book1);
        bookDAO.addItem(book2);

        List<Book> retrievedBooks = bookDAO.index();

        assertNotNull(retrievedBooks);
        assertEquals(2, retrievedBooks.size());
        assertTrue(retrievedBooks.contains(book1));
        assertTrue(retrievedBooks.contains(book2));
    }

    @Test
    void shouldGetItem() {
        Book book = new Book(1, "TestBook", "TestAuthor", 2022);
        bookDAO.addItem(book);

        Book retrievedBook = bookDAO.getItem(1);

        assertNotNull(retrievedBook);
        assertEquals(book, retrievedBook);
    }

    @Test
    void shouldUpdateItem() {
        Book originalBook = new Book(1, "TestBook", "TestAuthor", 2022);
        bookDAO.addItem(originalBook);

        Book updatedBook = new Book(1, "UpdatedBook", "UpdatedAuthor", 2023);
        bookDAO.updateItem(1, updatedBook);

        Book retrievedBook = bookDAO.getItem(1);
        assertNotNull(retrievedBook);
        assertEquals(updatedBook, retrievedBook);
    }

    @Test
    void shouldDeleteItem() {
        Book book = new Book(1, "TestBook", "TestAuthor", 2022);
        bookDAO.addItem(book);

        bookDAO.deleteItem(1);

        Book retrievedBook = bookDAO.getItem(1);
        assertNull(retrievedBook);
    }*/
}