package com.example.SpringPractice.common.exception;

/**
 * 書籍管理 API における共通のエラーメッセージ。
 */
public final class ErrorDetails {

  private ErrorDetails() {
    // Do Noting.
  }

  public static final String CREATE_BOOK_ERROR = "This book having this ID is already registered. ";

}
