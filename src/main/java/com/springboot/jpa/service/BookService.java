package com.springboot.jpa.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.jpa.controller.dto.BookRecordDto;
import com.springboot.jpa.entity.Book;
import com.springboot.jpa.entity.Review;
import com.springboot.jpa.repository.AuthorRepository;
import com.springboot.jpa.repository.BookRepository;
import com.springboot.jpa.repository.PublisherRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Transactional
    public Book saveBook(BookRecordDto bookRecordDto) {

        Book book = new Book();

        book.setTitle(bookRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDto.authorsIds()).stream().collect(Collectors.toSet()));

        Review review = new Review();
        review.setComment(bookRecordDto.reviewComment());
        review.setBook(book);
        book.setReview(review);

        return bookRepository.save(book);

    }

    public List<Book> getAllBooks() {

        return bookRepository.findAll();

    }

    @Transactional
    public void deleteBook(UUID id) {

        bookRepository.deleteById(id);

    }
}
