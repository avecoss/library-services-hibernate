package dev.alexcoss.models;

import jakarta.validation.constraints.*;

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
}
