package com.example.SpringPractice.domain;

import com.example.SpringPractice.common.exception.ApplicationException;
import com.example.SpringPractice.domain.client.BookService;
import com.example.SpringPractice.domain.model.Book;
import com.example.SpringPractice.domain.repository.BookRepository;
import com.example.SpringPractice.integration.Entity.BookEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("BookServiceのテスト")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceImplTest {

  @Autowired
  private BookService bookService;

  @MockBean
  BookRepository bookRepository;

  @Test
  void test_本の情報を適切に取得できる事() {

    //setup
    BookEntity bookEntity = BookEntity.builder()
                                      .id(1)
                                      .borrower(null)
                                      .price(3000)
                                      .title("アジャイルサムライ")
                                      .url("https://hoge.com")
                                      .build();

    when(bookRepository.findById(1)).thenReturn(bookEntity);

    Book expected = Book.builder()
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

  @Test
  void test_書籍情報を適切に登録できる事() {

    //setup
    BookEntity bookEntity = BookEntity.builder()
                                      .borrower(null)
                                      .price(3000)
                                      .title("アジャイルサムライ")
                                      .url("https://hoge.com")
                                      .build();

    BookEntity createdBookEntity = BookEntity.builder()
                                      .id(1)
                                      .borrower(null)
                                      .price(3000)
                                      .title("アジャイルサムライ")
                                      .url("https://hoge.com")
                                      .build();

    Book book = Book.builder()
                   .borrower(null)
                   .price(3000)
                   .title("アジャイルサムライ")
                   .url("https://hoge.com")
                   .build();

    when(bookRepository.save(bookEntity)).thenReturn(createdBookEntity);

    ////execute
    bookService.createBook(book);

    //assert
    verify(bookRepository).save(bookEntity);
  }

//  @Test
//  void test_既に存在するIDで書籍情報を追加しようとした場合ApplicationExceptionが発生する事() {
//
//    //setup
//    Optional<BookEntity> bookEntity = Optional.ofNullable(
//        BookEntity.builder()
//                  .id(1)
//                  .borrower(null)
//                  .price(3000)
//                  .title("アジャイルサムライ")
//                  .url("https://hoge.com")
//                  .build()
//    );
//
//    Book book = Book.builder()
//                   .id(1)
//                   .borrower(null)
//                   .price(3000)
//                   .title("アジャイルサムライ")
//                   .url("https://hoge.com")
//                   .build();
//
//    when(bookRepository.findById(1)).thenReturn(bookEntity);
//
//    ////execute
//    ApplicationException actual = assertThrows(ApplicationException.class, () -> bookService.createBook(book));
//
//    //assert
//    assertThat(actual.getErrorDetail()).isEqualTo(CREATE_BOOK_ERROR);
//
//  }

}
