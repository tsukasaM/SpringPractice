package com.example.SpringPractice.application.controller;


import com.example.SpringPractice.application.payload.BookPayload;
import com.example.SpringPractice.domain.client.BookService;
import com.example.SpringPractice.domain.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
public class BookController {

  private final BookService bookService;

  @GetMapping(value = "/book/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookPayload getBook(@PathVariable("id") Integer id ) {

    Book book = bookService.getBook(id);

    return BookPayload.of(book);
  }


}
