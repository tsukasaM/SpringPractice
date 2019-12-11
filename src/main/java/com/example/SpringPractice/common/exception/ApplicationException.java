package com.example.SpringPractice.common.exception;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;

/**
 * 書籍管理 API においてアプリケーション例外を表現するクラスです。
 */
@Value
@NonFinal
@Builder
public class ApplicationException extends RuntimeException {

  private final String errorDetail;

  /**
   * 指定されたエラー詳細を使用して {@link ApplicationException} を構築します。
   *
   * @param errorDetail エラー詳細
   */
  public ApplicationException(String errorDetail) {
    super();
    this.errorDetail = errorDetail;
  }
}
