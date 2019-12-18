package com.example.SpringPractice.application.controller;


import com.example.SpringPractice.application.payload.BookPayload;
import com.example.SpringPractice.domain.client.BookService;
import com.example.SpringPractice.domain.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
public class BookController {

  private final BookService bookService;

  /**
   * IDに紐付く書籍情報を取得するエンドポイントを提供します。
   *
   * @param id 取得する書籍情報のID
   * @return IDに紐付く書籍情報
   */
  @GetMapping(value = "/book/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookPayload getBook(@PathVariable("id") Integer id ) {

    Book book = bookService.getBook(id);

    return BookPayload.of(book);
  }

  /**
   * 新規に書籍情報を登録するためのエンドポイントを提供します。
   *
   * @param bookPayload 登録する書籍情報を表すペイロード
   */
  @PostMapping(value = "/book/create",
              consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void createBook(@RequestBody @Valid BookPayload bookPayload) {

    Book book = Book.builder()
                    .id(bookPayload.getId())
                    .title(bookPayload.getTitle())
                    .url(bookPayload.getUrl())
                    .price(bookPayload.getPrice())
                    .borrower(bookPayload.getBorrower())
                    .build();

    bookService.createBook(book);
  }

}
