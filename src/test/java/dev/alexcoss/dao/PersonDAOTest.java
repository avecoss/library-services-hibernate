package dev.alexcoss.dao;

import dev.alexcoss.models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import org.h2.jdbcx.JdbcDataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {

    private static final String CREATE_TABLE_SQL = "CREATE TABLE person\n" +
        "(\n" +
        "    id            SERIAL PRIMARY KEY,\n" +
        "    first_name    VARCHAR(100)                                                                             NOT NULL,\n" +
        "    last_name     VARCHAR(100)                                                                             NOT NULL,\n" +
        "    year_of_birth INTEGER CHECK (year_of_birth > 1900 AND year_of_birth < EXTRACT(YEAR FROM CURRENT_DATE)) NOT NULL\n" +
        ")";
    private static final String H2_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    private PersonDAO personDAO;
    private JdbcDataSource dataSource;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = new JdbcDataSource();
        dataSource.setURL(H2_URL);

        personDAO = new PersonDAO(new JdbcTemplate(dataSource));

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_SQL)) {
            preparedStatement.execute();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE person")) {
            preparedStatement.execute();
        }
    }

    @Test
    void shouldAddItem() {
        Person person = new Person(1, "John", "Doe", 1920);
        personDAO.addItem(person);

        List<Person> retrievedPerson = personDAO.index();

        assertNotNull(retrievedPerson);
        assertEquals(1, retrievedPerson.size());
        assertEquals(person, retrievedPerson.get(0));
    }

    @Test
    void shouldGetAllItems() {
        Person person = new Person(1, "John", "Doe", 1980);
        personDAO.addItem(person);

        List<Person> retrievedPerson = personDAO.index();

        assertNotNull(retrievedPerson);
        assertEquals(1, retrievedPerson.size());
        assertEquals(person, retrievedPerson.get(0));
    }

    @Test
    void shouldGetItem() {
        Person person = new Person(1, "John", "Doe", 1980);
        personDAO.addItem(person);

        Person retrievedPerson = personDAO.getItem(1);

        assertNotNull(retrievedPerson);
        assertEquals(person, retrievedPerson);
    }

    @Test
    void shouldUpdateItem() {
        Person originalPerson = new Person(1, "John", "Doe", 1980);
        personDAO.addItem(originalPerson);

        Person updatedPerson = new Person(1, "UpdatedJohn", "UpdatedDoe", 1990);
        personDAO.updateItem(1, updatedPerson);

        Person retrievedPerson = personDAO.getItem(1);
        assertNotNull(retrievedPerson);
        assertEquals(updatedPerson, retrievedPerson);
    }

    @Test
    void shouldDeleteItem() {
        Person person = new Person(1, "John", "Doe", 1980);
        personDAO.addItem(person);

        personDAO.deleteItem(1);

        Person retrievedPerson = personDAO.getItem(1);
        assertNull(retrievedPerson);
    }
}