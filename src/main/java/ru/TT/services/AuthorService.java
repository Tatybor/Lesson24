package ru.T.services;

import ru.T.DTO.AuthorDTO;

import java.util.List;

public interface AuthorService {
    public AuthorDTO getAuthorById (Long id);
    public AuthorDTO getAuthorBySurnameV1 (String surname);
    public AuthorDTO getAuthorBySurnameV2 (String surname);
    public AuthorDTO getAuthorBySurnameV3(String surname);
    public List<AuthorDTO> getAllAuthors();
}
