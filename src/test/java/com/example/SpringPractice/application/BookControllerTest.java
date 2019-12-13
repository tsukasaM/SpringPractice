package com.example.SpringPractice.application;

import com.example.SpringPractice.application.controller.BookController;
import com.example.SpringPractice.application.payload.BookPayload;
import com.example.SpringPractice.domain.client.BookService;
import com.example.SpringPractice.domain.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller に対するテスト")
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  BookService bookService;

  @Test
  void test_idを指定してGETでリクエストしたら該当する書籍が取得できる事() throws Exception {

    //setup
    Book book = Book.builder()
                    .id(1)
                    .borrower(null)
                    .price(3000)
                    .title("アジャイルサムライ")
                    .url("https://hoge.com")
                    .build();

    BookPayload bookPayload = BookPayload.builder()
                                        .id(1)
                                        .borrower(null)
                                        .price(3000)
                                        .title("アジャイルサムライ")
                                        .url("https://hoge.com")
                                        .build();

    ObjectMapper mapper = new ObjectMapper();
    String expected = mapper.writeValueAsString(bookPayload);

    when(bookService.getBook(1)).thenReturn(book);

    //execute
    mockMvc.perform(get("/v1/book/1"))
           .andExpect(status().isOk())
           .andExpect(content().json(expected));

    //assert
    verify(bookService).getBook(1);
  }

  @Test
  void test_POSTでリクエストして書籍情報が登録できる事() throws Exception {

    //setup
    Book book = Book.builder()
                    .id(1)
                    .borrower(null)
                    .price(3000)
                    .title("アジャイルサムライ")
                    .url("https://hoge.com")
                    .build();

    BookPayload bookPayload = BookPayload.builder()
                                         .id(1)
                                         .borrower(null)
                                         .price(3000)
                                         .title("アジャイルサムライ")
                                         .url("https://hoge.com")
                                         .build();

    ObjectMapper mapper = new ObjectMapper();
    String requestJson = mapper.writeValueAsString(bookPayload);

    doNothing().when(bookService).createBook(book);

    //execute
    mockMvc.perform(post("/v1/book/create")
           .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
           .content(requestJson))
           .andExpect(status().isNoContent());

    //assert
    verify(bookService).createBook(book);
  }

  @Test
  void test_ID無しで書籍情報を登録しようとするとApplicationExceptionが発生する事() throws Exception {

    //setup
    BookPayload bookPayload = BookPayload.builder()
                                         .id(null)
                                         .borrower(null)
                                         .price(3000)
                                         .title("アジャイルサムライ")
                                         .url("https://hoge.com")
                                         .build();

    ObjectMapper mapper = new ObjectMapper();
    String requestJson = mapper.writeValueAsString(bookPayload);

    //execute
    mockMvc.perform(post("/v1/book/create")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest());
  }


}
