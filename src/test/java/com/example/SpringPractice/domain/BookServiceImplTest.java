package com.example.SpringPractice.domain;

import com.example.SpringPractice.domain.client.BookService;
import com.example.SpringPractice.domain.model.Book;
import com.example.SpringPractice.integration.BookRepository;
import com.example.SpringPractice.integration.Entity.BookEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("BookServiceのテスト")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookServiceImplTest {

  @Autowired
  private BookService bookService;

  @MockBean
  BookRepository bookRepository;

  @Test
  void test_本の情報を適切に取得できる事() {

    //setup
    Optional<BookEntity> bookEntity = Optional.ofNullable(
        BookEntity.builder()
                  .id(1)
                  .borrower(null)
                  .price(3000)
                  .title("アジャイルサムライ")
                  .url("https://hoge.com")
                  .build()
    );

    when(bookRepository.findById(1)).thenReturn(bookEntity);

    Book expected = Book.builder()
                        .id(1)
                        .borrower(null)
                        .price(3000)
                        .title("アジャイルサムライ")
                        .url("https://hoge.com")
                        .build();

    ////execute
    Book actual = bookService.getBook(1);

    //assert
    assertEquals(expected, actual);
  }

}
