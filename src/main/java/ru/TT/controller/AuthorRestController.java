package ru.TT.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.TT.entity.Author;
import ru.TT.services.AuthorServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "авторы", description = "управление списком авторов")
public class AuthorRestController {

    private final AuthorServiceImpl authorServiceImpl;
    @GetMapping("/v2/authors")
    public ResponseEntity getAuthorBySurnameV2(@RequestParam("surname") String surname) {
        try {
            return ResponseEntity.ok(authorServiceImpl.getAuthorBySurnameV2(surname));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no such author in db");
        }
    }

    @GetMapping("/v3/authors")
    @Operation(summary = "позволяет получить автора по фамилии", description = "получает автора по фамилии используя спецификацию")
    public ResponseEntity getAuthorBySurnameV3(@RequestParam("surname") String surname) {
        try {
            return ResponseEntity.ok(authorServiceImpl.getAuthorBySurnameV3(surname));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no such author in db");
        }
    }

    @PostMapping("/authors")
    public ResponseEntity <?> addNewAuthor(@Valid @RequestBody Author author, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                errors.put(fieldError.getField(), objectError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().body(authorServiceImpl.addNewAuthor(author));
    }
}


