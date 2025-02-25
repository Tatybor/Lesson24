package ru.T.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.T.entity.Genre;
import ru.T.services.GenreServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreServiceImpl genreServiceImpl;

    @PostMapping("/genres")
    public ResponseEntity <?> addNewGenre(@Valid @RequestBody Genre genre, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                errors.put(fieldError.getField(), objectError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().body(genreServiceImpl.addNewGenre(genre));
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(genreServiceImpl.getGenreById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no such genre in DB");
        }
    }
}








