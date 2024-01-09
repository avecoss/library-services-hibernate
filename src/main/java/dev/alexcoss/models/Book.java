package dev.alexcoss.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {
    private static final int MIN_NAME = 2;
    private static final int MAX_NAME = 80;
    private static final int MIN_AUTHOR = 2;
    private static final int MAX_AUTHOR = 30;

    @Id
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = MIN_NAME, max = MAX_NAME, message = "Name should be between 2 and 80 characters")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = MIN_AUTHOR, max = MAX_AUTHOR, message = "Author should be between 2 and 30 characters")
    @Column(name = "author")
    private String author;
    @Min(value = 0, message = "Year should be greater than or equal to 0")
    @Column(name = "year_of_publishing")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person reader;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "taken_at")
    private Date takenAt;

    @Transient
    private boolean expired;

    public Book() {
    }

    public Book(String name, String author, Integer year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, year);
    }
}
