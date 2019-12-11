package com.example.SpringPractice.application.controller;

import com.example.SpringPractice.application.payload.ErrorResponse;
import com.example.SpringPractice.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 共通して例外のハンドリングを行うクラスです。
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  /**
   * ビジネス例外をハンドリングしてJson 形式のエラーを返却する処理を実行します。
   *
   * @param e 発生したビジネス例外
   * @return エラーレスポンス
   */
  @ExceptionHandler(ApplicationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<ErrorResponse> handleApplicationExceptionForRest(ApplicationException e) {
    return new ResponseEntity<>(new ErrorResponse(e.getErrorDetail()), HttpStatus.BAD_REQUEST);
  }
}
