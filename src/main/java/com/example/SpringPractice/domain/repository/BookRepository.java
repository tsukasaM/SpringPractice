package com.example.SpringPractice.domain.repository;

import com.example.SpringPractice.integration.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookRepository {

  /**
   * 書籍情報を登録します。
   *
   * @param bookEntity 新規登録した書籍情報
   * @return 新規登録された書籍情報
   */
  BookEntity save(BookEntity bookEntity);
}
