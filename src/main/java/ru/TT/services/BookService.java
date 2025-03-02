package ru.TT.services;

import ru.TT.DTO.BookDTO;

public interface BookService {
    BookDTO getBookById (Long id);
}
