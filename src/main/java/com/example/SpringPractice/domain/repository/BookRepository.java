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

  /**
   * 指定したidの書籍情報を取得します。
   *
   * @param id 取得したい書籍情報のid
   * @return 指定されたidの書籍情報
   */
  BookEntity findById(Integer id);
}
