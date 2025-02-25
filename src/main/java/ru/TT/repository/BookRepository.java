package ru.T.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.T.DTO.BookDTO;
import ru.T.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
