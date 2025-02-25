package ru.T.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.T.DTO.AuthorDTO;
import ru.T.DTO.BookDTO;
import ru.T.entity.Author;
import ru.T.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        log.info("нашли всех авторов");
        return authors.stream().map(this::convertAuthorToDTO).collect(Collectors.toList());
        //return authors.stream().map(author -> convertAuthorToDTO (author)).toList();
    }

    public Author addNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        log.info("нашли автора по id");
        return convertAuthorToDTO(author);
    }

    private AuthorDTO convertAuthorToDTO(Author author) {

        List<BookDTO> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDTO.builder()
                        //.genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
        log.info("получили из автора авторДТО");

        return AuthorDTO.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    @Override
    public AuthorDTO getAuthorBySurnameV1(String surname) {
        Author author = authorRepository.findAuthorBySurname(surname).orElseThrow();
        log.info("нашли автора по фамилии вариант1");
        return convertAuthorToDTO(author);
    }

    @Override
    public AuthorDTO getAuthorBySurnameV2(String surname) {
        Author author = authorRepository.findAuthorBySurnameV2(surname).orElseThrow();
        log.info("нашли автора по фамилии вариант2");
        return convertAuthorToDTO(author);
    }

    @Override
    public AuthorDTO getAuthorBySurnameV3(String surname) {
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("surname"), surname);
            }
        });
        Author author = authorRepository.findOne(specification).orElseThrow();
        log.info("нашли автора по фамилии вариант3");
        return convertAuthorToDTO(author);
    }
}









