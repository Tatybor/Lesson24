package ru.TT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.TT.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
