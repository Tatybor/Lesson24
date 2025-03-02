package ru.TT.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.TT.DTO.BookDTO;
import ru.TT.entity.Book;
import ru.TT.services.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookServiceImpl bookServiceImpl;

    @GetMapping("/books/{id}")
    BookDTO getBookById(@PathVariable("id") Long id) {
        return bookServiceImpl.getBookById(id);
    }

    @PostMapping("/v1/books")
    public Book addNewBook(@RequestBody Book book) {
        return bookServiceImpl.addNewBook(book);
    }

    @PostMapping("/v2/books")
    public ResponseEntity<?> addNewBook(@Valid @RequestBody BookDTO bookDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                errors.put(fieldError.getField(), objectError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().body(bookServiceImpl.addNewBook(bookDTO));
    }

    @PutMapping("/books")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDTO bookDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                errors.put(fieldError.getField(), objectError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(bookServiceImpl.updateBook(bookDTO));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookServiceImpl.deleteBook(id));
    }
}




