package com.example.SpringPractice.domain.client;


import com.example.SpringPractice.domain.model.Book;

public interface BookService {

  Book getBook(Integer id);
}
