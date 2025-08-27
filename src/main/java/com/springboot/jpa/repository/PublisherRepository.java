package com.springboot.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

}
