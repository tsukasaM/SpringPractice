package com.example.SpringPractice.integration;

import com.example.SpringPractice.integration.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Integer> {
}
