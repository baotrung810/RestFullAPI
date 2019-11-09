
package com.mycompany.spring_mvc_jpa_relationship.api;

import com.mycompany.spring_mvc_jpa_relationship.entities.BookEntity;
import com.mycompany.spring_mvc_jpa_relationship.model.Book;
import com.mycompany.spring_mvc_jpa_relationship.service.BookService;
import com.mycompany.spring_mvc_jpa_relationship.util.ConvertUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author SaT_LoP
 */
@RestController
@RequestMapping("/api")
public class BookRestFulAPI {
    
    @Autowired
    private BookService bookService;
    
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks(){
        List<BookEntity> bookEntitys = bookService.getBooks();
        if(bookEntitys !=null){
            List<Book> books = new ArrayList<Book>();
            for (BookEntity bookEntity : bookEntitys){
                books.add(ConvertUtil.convertBookFromBookEntity(bookEntity));
            }
            return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(@PathVariable("bookId") int bookId){
        BookEntity bookEntity = bookService.findBookById(bookId);
        if(bookEntity!=null){
            return new ResponseEntity<Book>(ConvertUtil.convertBookFromBookEntity(bookEntity),HttpStatus.OK);
        }else{
            return new ResponseEntity("Book with id"+ bookId +"isn't found", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value ="/book", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(
            @RequestBody BookEntity bookEntity,
            UriComponentsBuilder builder){
        bookEntity = bookService.save(bookEntity);
        if(bookEntity!=null && bookEntity.getId()>0){
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/api/book/{bookId}").buildAndExpand(bookEntity.getId()).toUri());
            return  new ResponseEntity(headers, HttpStatus.CREATED);
        }else{
            return new ResponseEntity("add new book fail.", HttpStatus.NOT_ACCEPTABLE);
        }
    }
     @RequestMapping(value ="/book", method = RequestMethod.PUT)
      public ResponseEntity<?> updateBook(   
            @RequestBody BookEntity bookEntity,
            UriComponentsBuilder builder){
          BookEntity book= bookService.findBookById(bookEntity.getId());
          if(book!=null){
              bookEntity = bookService.save(bookEntity);
               HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/api/book/{bookId}").buildAndExpand(bookEntity.getId()).toUri());
            return  new ResponseEntity(headers, HttpStatus.OK);
          }else{
               return new ResponseEntity("Book with id"+ bookEntity.getId()+"isn't found", HttpStatus.NOT_FOUND);
          }
      }
      
        @RequestMapping(value ="/book/{bookId}", method = RequestMethod.DELETE)
        public ResponseEntity<?> deleteBook(@PathVariable() int bookId){
             BookEntity bookEntity= bookService.findBookById(bookId);
          if(bookEntity!=null){
              bookService.deleteBookById(bookId);
           
            return  new ResponseEntity( HttpStatus.NO_CONTENT);
          }else{
               return new ResponseEntity("Book with id"+ bookId+"isn't found", HttpStatus.NOT_FOUND);
          }
        }
        
          @RequestMapping(value ="/search", method = RequestMethod.POST)
          public ResponseEntity<?> searchBook(
          @ModelAttribute("searchText") String searchText){
              List<BookEntity> bookEntitys = bookService.search(searchText);
              if(bookEntitys!=null){
                  List<Book> books = new ArrayList<Book>();
                  for(BookEntity bookEntity : bookEntitys){
                      books.add(ConvertUtil.convertBookFromBookEntity(bookEntity));
                  }
                   return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
              }else{
                   return new ResponseEntity(HttpStatus.NOT_FOUND);
              }
          }
}
