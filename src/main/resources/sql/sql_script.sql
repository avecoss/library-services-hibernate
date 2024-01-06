-- create tables
CREATE TABLE person
(
    id            SERIAL PRIMARY KEY,
    first_name    VARCHAR(100)                                                                             NOT NULL,
    last_name     VARCHAR(100)                                                                             NOT NULL,
    year_of_birth INTEGER CHECK (year_of_birth > 1900 AND year_of_birth < EXTRACT(YEAR FROM CURRENT_DATE)) NOT NULL
);

CREATE TABLE book
(
    id        SERIAL PRIMARY KEY,
    person_id INT          REFERENCES person (id) ON DELETE SET NULL,
    name      VARCHAR(255) NOT NULL,
    author    VARCHAR(100) NOT NULL,
    year      INTEGER CHECK (year < EXTRACT(YEAR FROM CURRENT_DATE))
);

-- create people
INSERT INTO person (first_name, last_name, year_of_birth)
VALUES ('Bob', 'Gray', 1992);
INSERT INTO person (first_name, last_name, year_of_birth)
VALUES ('Tom', 'Doe', 1987);
INSERT INTO person (first_name, last_name, year_of_birth)
VALUES ('Alice', 'Silver', 2001);
INSERT INTO person (first_name, last_name, year_of_birth)
VALUES ('Elizabet', 'Smith', 1986);
INSERT INTO person (first_name, last_name, year_of_birth)
VALUES ('Sam', 'Muller', 1986);

--create books
INSERT INTO book (name, author, year)
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 1925);
INSERT INTO book (name, author, year)
VALUES ('To Kill a Mockingbird', 'Harper Lee', 1960);
INSERT INTO book (name, author, year)
VALUES ('1984', 'George Orwell', 1949);
INSERT INTO book (name, author, year)
VALUES ('The Catcher in the Rye', 'J.D. Salinger', 1951);
INSERT INTO book (name, author, year)
VALUES ('Pride and Prejudice', 'Jane Austen', 1813);
INSERT INTO book (name, author, year)
VALUES ('The Hobbit', 'J.R.R. Tolkien', 1937);
INSERT INTO book (name, author, year)
VALUES ('The Lord of the Rings', 'J.R.R. Tolkien', 1954);
INSERT INTO book (name, author, year)
VALUES ('One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 1967);
INSERT INTO book (name, author, year)
VALUES ('The Da Vinci Code', 'Dan Brown', 2003);
INSERT INTO book (name, author, year)
VALUES ('Brave New World', 'Aldous Huxley', 1932);
INSERT INTO book (name, author, year)
VALUES ('The Shining', 'Stephen King', 1977);
INSERT INTO book (name, author, year)
VALUES ('The Great Expectations', 'Charles Dickens', 1861);
INSERT INTO book (name, author, year)
VALUES ('The Girl with the Dragon Tattoo', 'Stieg Larsson', 2005);
INSERT INTO book (name, author, year)
VALUES ('The War of the Worlds', 'H.G. Wells', 1897);
INSERT INTO book (name, author, year)
VALUES ('Moby-Dick', 'Herman Melville', 1851);


-- drop table
DROP TABLE person;
DROP TABLE book;

TRUNCATE TABLE person;
ALTER TABLE person
    ADD COLUMN address VARCHAR NOT NULL;


-- DML
SELECT *
FROM person;

SELECT *
FROM book;

SELECT p.* FROM book as b JOIN person as p ON b.person_id = p.id;
SELECT p.* FROM book as b JOIN person as p ON b.person_id = p.id WHERE b.id = 1;