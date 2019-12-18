package com.example.SpringPractice.it;

import com.example.SpringPractice.application.controller.BookController;
import com.example.SpringPractice.application.payload.BookPayload;
import com.example.SpringPractice.application.payload.ErrorResponse;
import com.example.SpringPractice.domain.client.BookService;
import com.example.SpringPractice.domain.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static com.example.SpringPractice.common.exception.ErrorDetails.CREATE_BOOK_ERROR;
import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("book エンドポイントに対するテスト")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
class BookApiIt {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private DataSource dataSource;

  private static final Operation DELETE_ALL = deleteAllFrom("books");
  private final Operation INSERT_BOOK_DATA = Operations.insertInto("books").columns("id",
                                                                                  "borrower",
                                                                                  "title",
                                                                                  "price",
                                                                                  "url")
                                                                                  .values( 1,
                                                                                      "萬年",
                                                                                      "アジャイルサムライ",
                                                                                      3000,
                                                                                      "https://hoge.com")
                                                                                  .values(2,
                                                                                      "tsukasa",
                                                                                      "hoge",
                                                                                      345,
                                                                                      "huga")
                                                                                  .build();

  private void dbSetup(Operation operation) {
    Destination destination = new DataSourceDestination(dataSource);
    DbSetup dbSetup = new DbSetup(destination, operation);
    dbSetup.launch();
  }

  @BeforeEach
  void setUp() {
    dbSetup(Operations.sequenceOf(
        DELETE_ALL,
        INSERT_BOOK_DATA
    ));
  }

  @AfterEach
  void tearDown() {
    dbSetup(Operations.sequenceOf(
        DELETE_ALL,
        INSERT_BOOK_DATA
    ));
  }

  @Test
  void test_book_itエンドポイントにGETでリクエストしたら該当する書籍が取得できる事() throws Exception {

    //setup
    BookPayload bookPayload = BookPayload.builder()
                                        .id(1)
                                        .borrower("萬年")
                                        .price(3000)
                                        .title("アジャイルサムライ")
                                        .url("https://hoge.com")
                                        .build();

    ObjectMapper mapper = new ObjectMapper();
    String expected = mapper.writeValueAsString(bookPayload);

    //execute
    mockMvc.perform(get("/v1/book/1"))
           .andExpect(status().isOk())
           .andExpect(content().json(expected));
  }

  @Test
  void test_book_createエンドポイントにPOSTでリクエストして書籍情報が登録できる事() throws Exception {

    //setup
    BookPayload bookPayload = BookPayload.builder()
                                         .id(3)
                                         .borrower(null)
                                         .price(3000)
                                         .title("アジャイルサムライ")
                                         .url("https://hoge.com")
                                         .build();

    ObjectMapper mapper = new ObjectMapper();
    String requestJson = mapper.writeValueAsString(bookPayload);


    //execute
    mockMvc.perform(post("/v1/book/create")
           .contentType(MediaType.APPLICATION_JSON_VALUE)
           .content(requestJson))
           .andExpect(status().isNoContent());

  }

  @Test
  void test_book_createエンドポイントに既に存在するIDの書籍情報を登録しようとすると400エラーが返却されてApplicationExceptionが発生する事() throws Exception {

    //setup
    BookPayload bookPayload = BookPayload.builder()
                                         .id(1)
                                         .borrower(null)
                                         .price(3000)
                                         .title("アジャイルサムライ")
                                         .url("https://hoge.com")
                                         .build();

    ObjectMapper mapper = new ObjectMapper();
    String requestJson = mapper.writeValueAsString(bookPayload);

    String errorJson =  mapper.writeValueAsString(new ErrorResponse(CREATE_BOOK_ERROR));

    //execute
    mockMvc.perform(post("/v1/book/create")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(content().json(errorJson));
  }


}
