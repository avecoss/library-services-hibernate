package dev.alexcoss.services;

import dev.alexcoss.models.Book;
import dev.alexcoss.models.Person;
import dev.alexcoss.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private static final int DAYS = 10;

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        return person.map(this::processBooks).orElse(Collections.emptyList());
    }

    private List<Book> processBooks(Person person) {
        Hibernate.initialize(person.getBooks());

        calculateExpirationStatus(person);
        return person.getBooks();
    }

    private void calculateExpirationStatus(Person person) {
        final long daysInMilliseconds = Duration.ofDays(DAYS).toMillis();

        person.getBooks().forEach(book -> {
            long milliseconds = Math.abs(book.getTakenAt().getTime() - System.currentTimeMillis());
            book.setExpired(milliseconds > daysInMilliseconds);
        });
    }
}
