package com.example.SpringPractice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {

  private Integer id;

  private String borrower;

  private String title;

  private Integer price;

  private String url;

}
