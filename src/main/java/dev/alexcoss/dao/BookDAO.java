package dev.alexcoss.dao;

import dev.alexcoss.models.Book;
import dev.alexcoss.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO extends AbstractDao<Book> {

    private static final String INSERT_SQL = "INSERT INTO book (person_id, name, author, year) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM book";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM book WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE book SET person_id = ?, name = ?, author = ?, year = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM book WHERE id = ?";
    private static final String SELECT_BOOKS_PERSON_SQL = "SELECT p.* FROM book as b JOIN person as p ON b.person_id = p.id WHERE b.id = ?";
    private static final String UPDATE_PERSON_ID_NULL_SQL = "UPDATE book SET person_id = NULL WHERE id = ?";
    private static final String UPDATE_PERSON_ID_SQL = "UPDATE book SET person_id = ? WHERE id = ?";


    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Book> index() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book getItem(int id) {
        return jdbcTemplate.query(SELECT_BY_ID_SQL, new BeanPropertyRowMapper<Book>(Book.class), id)
            .stream()
            .findAny()
            .orElse(null);
    }

    @Override
    public void addItem(Book book) {
        jdbcTemplate.update(INSERT_SQL, book.getPersonId(), book.getName(), book.getAuthor(), book.getYear());
    }

    @Override
    public void updateItem(int id, Book updateBook) {
        jdbcTemplate.update(UPDATE_SQL, updateBook.getPersonId(), updateBook.getName(), updateBook.getAuthor(), updateBook.getYear(), id);
    }

    @Override
    public void deleteItem(int id) {
        System.out.println(id);
        jdbcTemplate.update(DELETE_SQL, id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query(SELECT_BOOKS_PERSON_SQL,  new BeanPropertyRowMapper<>(Person.class), id)
            .stream()
            .findAny();
    }

    public void releaseItem(int id) {
        jdbcTemplate.update(UPDATE_PERSON_ID_NULL_SQL, id);
    }

    public void assignItems(int id, Person selectedPerson) {
        jdbcTemplate.update(UPDATE_PERSON_ID_SQL, selectedPerson.getId(), id);
    }
}
