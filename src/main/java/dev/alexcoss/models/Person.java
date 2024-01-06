package dev.alexcoss.models;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class Person {
    private static final int MIN = 2;
    private static final int MAX = 30;
    private static final int MIN_YEAR = 1920;

    private int id;
    @NotEmpty(message = "First name should not be empty")
    @Size(min = MIN, max = MAX, message = "First name should be between 2 and 30 characters")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = MIN, max = MAX, message = "Last name should be between 2 and 30 characters")
    private String lastName;
    @Min(value = MIN_YEAR, message = "Year of Birth should be after 1920")
    private int yearOfBirth;

    public Person() {}

    public Person(int id, String firstName, String lastName, int yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && yearOfBirth == person.yearOfBirth && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, yearOfBirth);
    }
}
