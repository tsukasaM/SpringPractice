/*
 * (c)Copyright Since 2019, SOFTBANK Corp. All rights reserved.
 *
 */

package com.example.SpringPractice.integration;

import com.example.SpringPractice.domain.repository.BookRepository;
import com.example.SpringPractice.integration.Entity.BookEntity;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {

  @Autowired
  private BookRepository target;

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
                                                                                      null)
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
        DELETE_ALL
    ));
  }

  @Test
  void test_書籍情報が適切に保存できる事() {

   BookEntity bookEntity = BookEntity.builder()
                                     .borrower(null)
                                     .price(3000)
                                     .title("アジャイルサムライ")
                                     .url("https://hoge.com")
                                     .build();

    BookEntity actual = target.save(bookEntity);

    BookEntity expected = BookEntity.builder()
                                    .id(3)
                                    .borrower(null)
                                    .price(3000)
                                    .title("アジャイルサムライ")
                                    .url("https://hoge.com")
                                    .build();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void test_書籍情報が適切に取得できる事() {

   BookEntity expected = BookEntity.builder()
                                     .id(1)
                                     .borrower("萬年")
                                     .price(3000)
                                     .title("アジャイルサムライ")
                                     .url(null)
                                     .build();

    BookEntity actual = target.findById(1);

    assertThat(actual).isEqualTo(expected);
  }

}
