package ru.TT.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.TT.DTO.BookDTO;
import ru.TT.entity.Book;
import ru.TT.entity.Genre;
import ru.TT.repository.BookRepository;
import java.util.HashSet;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    void addNewBook() {
        Book newBook = new Book(10L, "Unesennye vetrom", new Genre(), new HashSet<>());
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(newBook);
        Book bookRes = bookServiceImpl.addNewBook(newBook);
        assertNotNull(bookRes);
        assertEquals(newBook.getName(), bookRes.getName());
    }

    @Test
    void getBookById() {
        long id = 9L;
        Book newBook1 = new Book(id, "100 let odinochestva", new Genre(), new HashSet<>());
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(newBook1));
        BookDTO bookRes = bookServiceImpl.getBookById(id);
        assertNotNull(bookRes);
        assertEquals(newBook1.getName(), bookRes.getName());
    }

    @Test
    void deleteBook() {
        long id = 7L;
        Book newBook3 = new Book(id, "Kalasy pad syarpom tvaim", new Genre(), new HashSet<>());
        String result = bookServiceImpl.deleteBook(id);
        assertEquals("book with id 7 was successfully deleted", result);
    }
}
