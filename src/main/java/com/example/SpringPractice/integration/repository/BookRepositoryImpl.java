/*
 * (c)Copyright Since 2019, SOFTBANK Corp. All rights reserved.
 *
 */

package com.example.SpringPractice.integration.repository;

import com.example.SpringPractice.domain.repository.BookRepository;
import com.example.SpringPractice.integration.Entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * {@link BookRepository} の実装クラスです。
 */
@Repository
public class BookRepositoryImpl implements BookRepository {


  @Autowired
  EntityManager entityManager;

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public BookEntity save(BookEntity bookEntity) {
    entityManager.persist(bookEntity);
    return bookEntity;
  }

  @Override
  public BookEntity findById(Integer id) {
    return null;
  }


}
