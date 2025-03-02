package ru.TT.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.TT.DTO.AuthorDTO;
import ru.TT.DTO.BookDTO;
import ru.TT.DTO.GenreDTO;
import ru.TT.entity.Genre;
import ru.TT.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public Genre addNewGenre(Genre genre) {
        log.info("добавили новый жанр");
        return genreRepository.save(genre);
    }

    @Override
    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        log.info("нашли жанр по id");
        return convertToDTO(genre);
    }

    private GenreDTO convertToDTO(Genre genre) {
        List<BookDTO> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> BookDTO.builder()
                        //.genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .authors(book.getAuthors().stream()  // dob avt
                                .map(author -> AuthorDTO.builder()
                                        .id(author.getId())
                                        .name(author.getName())
                                        .surname(author.getSurname())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        log.info("список книгДТО");

        return GenreDTO.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
