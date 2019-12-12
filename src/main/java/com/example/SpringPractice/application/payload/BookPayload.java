package com.example.SpringPractice.application.payload;

import com.example.SpringPractice.domain.model.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 書籍情報を表現するペイロード。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookPayload {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("borrower")
  private String borrower;

  @JsonProperty("title")
  private String title;

  @JsonProperty("price")
  private Integer price;

  @JsonProperty("url")
  private String url;

  public static BookPayload of(Book book) {
    return BookPayload.builder()
                      .id(book.getId())
                      .title(book.getTitle())
                      .borrower(book.getBorrower())
                      .price(book.getPrice())
                      .url(book.getUrl())
                      .build();
  }


}
