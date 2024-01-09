package dev.alexcoss.utils;

import dev.alexcoss.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals((clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (person.getDateOfBirth() == null) {
            errors.rejectValue("dateOfBirth", "", "Date of Birth cannot be null");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(person.getDateOfBirth().toString());
        } catch (ParseException e) {
            errors.rejectValue("dateOfBirth", "", "Incorrect date format. Please use the format dd/MM/yyyy");
        }
    }
}
