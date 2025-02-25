package ru.T.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.T.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
