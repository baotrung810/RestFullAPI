
package com.mycompany.spring_mvc_jpa_relationship.service;

import com.mycompany.spring_mvc_jpa_relationship.entities.BookDetailEntity;
import com.mycompany.spring_mvc_jpa_relationship.entities.BookEntity;
import com.mycompany.spring_mvc_jpa_relationship.repository.BookDetailRepository;
import com.mycompany.spring_mvc_jpa_relationship.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookDetailRepository bookDetailRepository;
    
    public List<BookEntity> getBooks(){
//        return (List<BookEntity>) bookRepository.findAll();
        return bookRepository.findByOrderByAuthorDesc();
    }
    public BookEntity findBookById(int bookId){
        return bookRepository.findOne(bookId);
    }
    
    public BookEntity save(BookEntity book){
        if(book !=null && book.getId() >0){
            bookRepository.save(book);
            bookDetailRepository.save(book.getBookDetail());
        }else{
            BookDetailEntity detailEntity = bookDetailRepository.save(book.getBookDetail());
            book.setBookDetail(detailEntity);
            bookRepository.save(book);
        }
        return book;
    }
    
    public boolean deleteBookById(int id) {
        bookRepository.delete(id);
        return bookRepository.exists(id) ;
    }
    
    public List<BookEntity> search(String searchTxt){
//       return bookRepository.findByNameContainingOrAuthorContainingOrderByAuthorAsc(searchTxt, searchTxt);
//          return bookRepository.findBookByCategory("%"+searchTxt+"%");
        return bookRepository.findBookByCategoryNative("%"+searchTxt+"%");
    }
}
