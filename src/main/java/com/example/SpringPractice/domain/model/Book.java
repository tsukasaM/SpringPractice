package com.example.SpringPractice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 書籍情報のドメインモデルを表します。
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {

  private String borrower;

  private String title;

  private Integer price;

  private String url;

}
