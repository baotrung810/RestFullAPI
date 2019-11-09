/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_jpa_relationship.util;

import com.mycompany.spring_mvc_jpa_relationship.entities.BookEntity;
import com.mycompany.spring_mvc_jpa_relationship.model.Book;
import com.mycompany.spring_mvc_jpa_relationship.model.BookDetail;
import com.mycompany.spring_mvc_jpa_relationship.model.Category;
import java.text.SimpleDateFormat;

/**
 *
 * @author SaT_LoP
 */
public class ConvertUtil {
    
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static Book convertBookFromBookEntity(BookEntity bookEntity){
        Category category = new Category(
        bookEntity.getCategory().getId(),
        bookEntity.getCategory().getName(),
        bookEntity.getCategory().getDescription());
        
        BookDetail bookDetail = new BookDetail(
        bookEntity.getBookDetail().getId(),
        bookEntity.getBookDetail().getIsbn(),
        bookEntity.getBookDetail().getNumberOfPage(),
        bookEntity.getBookDetail().getPrice(),
        formatter.format(bookEntity.getBookDetail().getPublishDate()),
        bookEntity.getBookDetail().getDescription());
        
        return new Book(bookEntity.getId(), bookEntity.getName(),bookEntity.getAuthor(), category, bookDetail);
    }
}
