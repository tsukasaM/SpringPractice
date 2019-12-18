package com.example.SpringPractice.common.exception;

/**
 * 書籍管理 API における共通のエラーメッセージ。
 */
public final class ErrorDetails {

  private ErrorDetails() {
    // Do Noting.
  }

  /** 同じ ID の書籍を登録しようとした際に表示されるエラーメッセージ。 */
  public static final String CREATE_BOOK_ERROR = "This book having this ID is already registered. ";

}
