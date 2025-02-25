package ru.T.services;

import ru.T.DTO.BookDTO;

public interface BookService {
    BookDTO getBookById (Long id);
}
