package com.springboot.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.controller.dto.BookRecordDto;
import com.springboot.jpa.entity.Book;
import com.springboot.jpa.service.BookService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/bookstore/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody BookRecordDto bookRecordDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));

    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {

        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
