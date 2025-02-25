package ru.T.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.T.entity.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
    Optional<Author> findAuthorBySurname(String surname);  //нельзя менять назв метода тк он jpa шный

    @Query(nativeQuery = true, value = "select * from author where surname=?")
    Optional<Author> findAuthorBySurnameV2(String surname);

   }
