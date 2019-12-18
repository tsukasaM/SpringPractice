/*
 * (c)Copyright Since 2019, SOFTBANK Corp. All rights reserved.
 *
 */

package com.example.SpringPractice.integration;

import com.example.SpringPractice.integration.Entity.BookEntity;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {

  @Autowired
  private BookMapper target;

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
        DELETE_ALL
    ));
  }

  @AfterEach
  void tearDown() {
    dbSetup(Operations.sequenceOf(
        DELETE_ALL
    ));
  }

  @Test
  void test_書籍情報が適切に取得できる事() {

   BookEntity bookEntity = BookEntity.builder()
                                     .id(1)
                                     .borrower(null)
                                     .price(3000)
                                     .title("アジャイルサムライ")
                                     .url("https://hoge.com")
                                     .build();

    target.save(bookEntity);
    

  }

}
