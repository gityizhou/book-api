package com.zhouyi.book.dao;

import com.zhouyi.book.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByAuthorAndStatus(String author, int status);

    List<Book> findBooksByDescriptionContains(String des);

    Page<Book> findAll(Pageable pageable);

    //@Query("select b from Book b where length(b.name) > ?1")
    @Query(value = "select * from book where length(name) > ?1 ", nativeQuery = true)
    List<Book> findByJPQL(int len);

    @Modifying
    @Query(value = "update Book b set b.status = ?1 where b.id = ?2")
    @Transactional
    int updateByJPQL(int status, long id);

    @Modifying
    @Transactional
    @Query(value = "delete from Book b where b.id=?1 ")
    int deleteByJPQL(Long id);
}
