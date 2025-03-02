package ru.TT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.TT.entity.UserOfLibrary;

public interface UserOfLibraryRepository extends JpaRepository<UserOfLibrary, Long> {
public UserOfLibrary findUserOfLibraryByEmail(String email);
}
