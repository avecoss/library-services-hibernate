# Library Service
The library service is implemented using Spring MVC, Spring Data JPA, and Hibernate. Spring Boot has intentionally not been used in this implementation. The application allows users to add and remove books borrowed by readers, add new books to the library, and perform deletion and editing of books in the library. It also includes a simple reader profile creation feature with the ability to view currently borrowed books. The logic is similar to a traditional library where a borrowed book becomes unavailable to other readers. User profiles can also be edited or deleted.

## Features
### Book Pagination
Pagination for books is implemented. Examples:

- `/books?page=1&books_per_page=3`
- `/books`

### Book Sorting by Year
Books can be sorted by year. Examples:
- `/books?sort_by_year=true`
- `/books`

### Combined Features
All features can be combined. Example:
- `/books?page=1&books_per_page=3&sort_by_year=true`

### Book Search
A separate page for searching books is available at `/books/search`.

## How to Use

1. **Clone the repository.**
    ```bash
    git clone <repository_url>
    ```

2. **Navigate to the project directory.**
    ```bash
    cd <project_directory>
    ```

3. **Build the project.**
    ```bash
    mvn clean install
    ```

4. **Access the application in a web browser at [http://localhost:8080](http://localhost:8080).**

## Contributors

- [avexcoss](https://github.com/avecoss)





