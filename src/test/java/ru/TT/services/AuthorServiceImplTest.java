package ru.TT.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.TT.DTO.AuthorDTO;
import ru.TT.entity.Author;
import ru.TT.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;

    @Test
    void getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(10L, "Korney", "Chukovskiy", new HashSet<>()));
        authors.add(new Author(9L, "Yakub", "Kolas", new HashSet<>()));
        Mockito.when(authorRepository.findAll()).thenReturn(authors);
        List<AuthorDTO> authorsRes = authorServiceImpl.getAllAuthors();
        assertNotNull(authorsRes);
        assertEquals("Korney", authors.get(0).getName());
    }

    @Test
    void addNewAuthor() {
        Author newAuthor = new Author(12L, "Yanka", "Kupala", new HashSet<>());
        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(newAuthor);
        Author result = authorServiceImpl.addNewAuthor(newAuthor);
        assertNotNull(result);
        assertEquals(newAuthor.getId(), result.getId());
        assertEquals(newAuthor.getName(), result.getName());
        assertEquals(newAuthor.getSurname(), result.getSurname());
    }

    @Test
    void getAuthorBySurnameV1() {
        String name = "Hans";
        String surname = "Andersen";
        Author newAuthor2 = new Author(15L, name, surname, new HashSet<>());
        Mockito.when(authorRepository.findAuthorBySurname(surname)).thenReturn(Optional.of(newAuthor2));
        AuthorDTO authorResult = authorServiceImpl.getAuthorBySurnameV1(newAuthor2.getSurname());
        assertNotNull(authorResult);
        assertEquals(newAuthor2.getId(), authorResult.getId());
        assertEquals(newAuthor2.getName(), authorResult.getName());
        assertEquals(newAuthor2.getSurname(), authorResult.getSurname());
    }
}


