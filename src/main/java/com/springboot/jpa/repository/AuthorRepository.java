package com.springboot.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
