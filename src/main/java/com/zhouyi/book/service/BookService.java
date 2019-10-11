package com.zhouyi.book.service;

import com.zhouyi.book.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {


    //查所有书籍
    List<Book> getAll();

    //翻页查询功能加入
    Page<Book> findAllByPage(Pageable pageable);

    //按照id查看指定书籍
    Book getById(Long id);

    //添加或者修改一本书籍
    Book saveBook(Book book);

    //删除一本书籍
    Book deleteBook(Long id);

    //通过作者查找书籍
    List<Book> getByAuthor(String author);

    //通过作者和状态码查找书籍
    List<Book> getByAuthorAndStatus(String author, int status);

    // 通过description的结尾查找书籍
    List<Book> getByDescriptionContains(String des);

    // 自定义查找
    List<Book> findByJPQL(int len);

    // 自定义更新
    int updateByJPQL(int status, long id);

    // 自定义删除
    int deleteByJPQL(Long id);

    // 事务测试
    public int deleteAndUpdate(Long id, int status, Long uid);



}
