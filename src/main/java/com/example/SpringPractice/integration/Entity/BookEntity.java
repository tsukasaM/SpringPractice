package com.example.SpringPractice.integration.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * books テーブルのエンティティクラスです。
 */
@Builder
@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "borrower")
  private String borrower;

  @Column(name = "title")
  private String title;

  @Column(name = "price")
  private Integer price;

  @Column(name = "url")
  private String url;

}
