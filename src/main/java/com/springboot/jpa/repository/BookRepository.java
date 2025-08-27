package com.springboot.jpa.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.jpa.entity.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Book findBookByTitle(String title);

    @Query(value = "SELECT * FROM tb_book WHERE publisher_id = :id", nativeQuery = true)
    List<Book> findBooksByPublisherId(@Param("id") UUID id);

}
