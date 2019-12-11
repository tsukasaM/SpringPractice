package com.example.SpringPractice.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * エラーが発生したときの詳細情報を表すクラス。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
  @JsonProperty("message")
  private String message;
}
