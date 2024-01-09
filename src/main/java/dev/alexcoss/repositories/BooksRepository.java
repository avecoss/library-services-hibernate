package dev.alexcoss.repositories;

import dev.alexcoss.models.Book;
import dev.alexcoss.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b.reader FROM Book as b JOIN FETCH b.reader WHERE b.id = :bookId")
    Optional<Person> findReader(@Param("bookId") int bookId);

    List<Book> findByNameStartingWith(String name);
}
