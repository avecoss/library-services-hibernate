package dev.alexcoss.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDao<T> implements Dao<T> {
    protected final JdbcTemplate jdbcTemplate;

    public AbstractDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
