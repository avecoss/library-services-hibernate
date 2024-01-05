package dev.alexcoss.dao;

import dev.alexcoss.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO extends AbstractDao<Person> {

    private static final String INSERT_SQL = "INSERT INTO person (first_name, last_name, year_of_birth) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM person";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM person WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE person SET first_name = ?, last_name = ?, year_of_birth = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM person WHERE id = ?";


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Person> index() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Person getItem(int id) {
        return jdbcTemplate.query(SELECT_BY_ID_SQL, new BeanPropertyRowMapper<Person>(Person.class), id)
            .stream()
            .findAny()
            .orElse(null);
    }

    @Override
    public void addItem(Person person) {
        jdbcTemplate.update(INSERT_SQL, person.getFirstName(), person.getLastName(), person.getYearOfBirth());
    }

    @Override
    public void updateItem(int id, Person updatePerson) {
        jdbcTemplate.update(UPDATE_SQL, updatePerson.getFirstName(), updatePerson.getLastName(), updatePerson.getYearOfBirth(), id);
    }

    @Override
    public void deleteItem(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }
}
