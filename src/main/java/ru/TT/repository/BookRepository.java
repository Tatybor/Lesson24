package ru.TT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.TT.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
