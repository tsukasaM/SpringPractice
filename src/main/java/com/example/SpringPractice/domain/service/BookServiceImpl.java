package com.example.SpringPractice.domain.service;

import com.example.SpringPractice.common.exception.ApplicationException;
import com.example.SpringPractice.domain.client.BookService;
import com.example.SpringPractice.domain.model.Book;
import com.example.SpringPractice.domain.repository.BookRepository;
import com.example.SpringPractice.integration.Entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.SpringPractice.common.exception.ErrorDetails.CREATE_BOOK_ERROR;

/**
 * {@link BookService} の実装クラスです。
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

  @Autowired
  BookRepository bookRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public Book getBook(Integer id) throws NullPointerException {


    BookEntity bookEntity = bookRepository.findById(id);

    return Book.builder()
               .borrower(bookEntity.getBorrower())
               .price(bookEntity.getPrice())
               .url(bookEntity.getUrl())
               .title(bookEntity.getTitle())
               .build();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createBook(Book book) {

    BookEntity bookEntity = BookEntity.builder()
                                      .title(book.getTitle())
                                      .borrower(book.getBorrower())
                                      .price(book.getPrice())
                                      .url(book.getUrl())
                                      .build();

//    if (bookRepository.findById(book.getId()).isPresent()) {
//      throw new ApplicationException(CREATE_BOOK_ERROR);
//    } else {
      bookRepository.save(bookEntity);
    //}
  }
}
