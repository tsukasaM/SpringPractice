package com.example.SpringPractice.domain.service;

import com.example.SpringPractice.domain.client.BookService;
import com.example.SpringPractice.domain.model.Book;
import com.example.SpringPractice.integration.BookRepository;
import com.example.SpringPractice.integration.Entity.BookEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

  @Autowired
  BookRepository bookRepository;

  @Override
  public Book getBook(Integer id) throws NullPointerException {

    Book book = new Book();

    bookRepository.findById(id).ifPresent(bookEntity -> {
      book.setId(bookEntity.getId());
      book.setTitle(bookEntity.getTitle());
      book.setPrice(bookEntity.getPrice());
      book.setUrl(bookEntity.getUrl());
      book.setBorrower(bookEntity.getBorrower());
    });

    return book;
  }
}
