package com.springboot.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

}
