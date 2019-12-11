package com.example.SpringPractice.domain.client;


import com.example.SpringPractice.domain.model.Book;

public interface BookService {

  /**
   * idで指定した書籍情報を取得します。
   *
   * @param id 取得したい書籍のid
   * @return idに紐付く書籍情報
   */
  Book getBook(Integer id);

  /**
   * 書籍情報を登録します。
   *
   * @param book 登録したい書籍情報
   */
  void createBook(Book book);
}
