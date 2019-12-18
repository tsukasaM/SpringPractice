package com.example.SpringPractice.integration;

import com.example.SpringPractice.integration.Entity.BookEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface BookMapper {

//  @Select("select * from books")
//  List<BookEntity> findAll();

  @Select("select * from books where id = #{id}")
  BookEntity findOne(int id);

  @Insert("insert into books (borrower, title, price, url) "
      + "values (#{borrower}, #{title}, #{price}, #{url})")
  @Options(useGeneratedKeys = true,keyProperty = "id")
  void save(BookEntity book);

//  @Update("update books set "
//      + "borrower = #{borrower}, title = #{title}, price = #{price}, url = #{url} where id = #{id}")
//  void update(BookEntity book);
//
//  @Delete("delete from books where id = #{id}")
//  void delete(int id);
}
