package com.zhouyi.book.controller;

import com.zhouyi.book.bean.Book;
import com.zhouyi.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookApi {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public Page<Book> getAllBooks(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return bookService.findAllByPage(pageable);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping("/books")
    public Book saveBook(Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/books")
    public Book updateBook(Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/books/{id}")
    public Book deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @PostMapping("/books/by")
    public int findBy(@RequestParam Long id, @RequestParam int status, @RequestParam Long uid) {
//        return bookService.getByAuthor(author);
//        return bookService.getByAuthorAndStatus(author, status);
//        return bookService.getByDescriptionContains(des);
//        return bookService.findByJPQL(len);
        return bookService.deleteAndUpdate(id, status, uid);
    }

    @PutMapping("/books/by")
    public int updateByJPQL(@RequestParam int status, @RequestParam long id) {
        return bookService.updateByJPQL(status, id);
    }

    @DeleteMapping("/books/by")
    public int deleteByJPQL(@RequestParam long id) {
        return bookService.deleteByJPQL(id);
    }

}
