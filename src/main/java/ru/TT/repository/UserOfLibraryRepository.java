package ru.T.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.T.entity.UserOfLibrary;

public interface UserOfLibraryRepository extends JpaRepository<UserOfLibrary, Long> {
public UserOfLibrary findUserOfLibraryByEmail(String email);
}
