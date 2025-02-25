package ru.T.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.T.DTO.AuthorDTO;
import ru.T.DTO.BookDTO;
import ru.T.entity.Book;
import ru.T.entity.Genre;
import ru.T.repository.BookRepository;
import ru.T.repository.GenreRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public Book addNewBook(Book book) {
        log.info("добавили новую книгу");
        return bookRepository.save(book);
    }

    public Book addNewBook(BookDTO bookDTO) {
        Genre newBookGenre = genreRepository.getReferenceById(bookDTO.getId());
        Book newBook = new Book();
        newBook.setName(bookDTO.getName());
        newBook.setGenre(newBookGenre);
        log.info("добавили новую книгу через ДТО");
        return bookRepository.save(newBook);
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        log.info("нашли книгу по id");
        return convertToDTO(book);
    }

    private BookDTO convertToDTO(Book book) {

        List<AuthorDTO> authorDtoList = book.getAuthors()
                .stream()
                .map(author -> AuthorDTO.builder()
                        .surname(author.getSurname())
                        .name(author.getName())
                        .id(author.getId())
                        .build()
                ).toList();
        log.info("получили из книги книгаДТО");

        return BookDTO.builder()
                .authors(authorDtoList)
                .id(book.getId())
                .name(book.getName())
                //.genre(book.getGenre().getName())
                .build();
    }

    public Book updateBook(BookDTO bookDTO) {
        Book book = bookRepository.getById(bookDTO.getId());
        Optional.ofNullable(bookDTO.getName()).ifPresent(book::setName);
        log.info("обновили книгу");
        return bookRepository.save(book);
    }

    public String deleteBook(Long id) {
        Book book = bookRepository.getById(id);
        try {
            bookRepository.delete(book);
            log.info("удалили книгу");
            return "book with id " + id + " was successfully deleted";
        } catch (Exception e) {
            log.info("не удалось удалить книгу");
            return "book with id " + id + " couldn't be deleted";
        }
    }
}
