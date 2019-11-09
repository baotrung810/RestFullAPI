package com.mycompany.spring_mvc_jpa_relationship.repository;

import com.mycompany.spring_mvc_jpa_relationship.entities.BookEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer>/*ham dung sap xep: PagingAndSortingRepository<Object, Serializable>*/ {

    List<BookEntity> findByNameLikeOrAuthor(String name, String author);

    List<BookEntity> findByNameContainingOrAuthorContainingOrderByAuthorAsc(String name, String author);

    List<BookEntity> findByOrderByAuthorDesc();

//    JSQL
    @Query("Select b from BookEntity b "
            + "where b.category.name like ?1 "
            + "order by b.bookDetail.price Asc")
    List<BookEntity> findBookByCategory(String categoryName);

//    Native
    @Query(value = "Select b.id, b.name, b.author, c.id as category_id, c.name as category_name, "
            + "bd.number_of_page, bd.price, bd.publishDate, bd.description, bd.isbn, bd.id as book_detail_id "
            + " from book b "
            + "join category c on b.category_id = c.id "
            + "join bookdetail bd on bd.id=b.book_detail_id "
            + "where c.name like ?1 "
            + "Order by bd.price asc", nativeQuery = true)
    List<BookEntity> findBookByCategoryNative(String categoryName);
}
