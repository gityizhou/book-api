package com.zhouyi.book.service;

import com.zhouyi.book.bean.Book;
import com.zhouyi.book.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllByPage(Pageable pageable) {
//        Sort sort = Sort.by(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(1,5,sort);
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book deleteBook(Long id) {
        Book b = getById(id);
        bookRepository.deleteById(id);
        return b;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author);
    }

    @Override
    public List<Book> getByAuthorAndStatus(String author, int status) {
        return bookRepository.findBooksByAuthorAndStatus(author, status);
    }

    @Override
    public List<Book> getByDescriptionContains(String des) {
        return bookRepository.findBooksByDescriptionContains(des);
    }

    @Override
    public List<Book> findByJPQL(int len) {
        return bookRepository.findByJPQL(len);
    }

    @Override
    @Transactional
    public int updateByJPQL(int status, long id) {
        return bookRepository.updateByJPQL(status, id);
    }

    @Override
    @Transactional
    public int deleteByJPQL(Long id) {
        return bookRepository.deleteByJPQL(id);
    }

    @Override
    @Transactional
    public int deleteAndUpdate(Long id, int status, Long uid) {
        int dcount = bookRepository.deleteByJPQL(id);
        int ucount = bookRepository.updateByJPQL(status, uid);
        return dcount + ucount;
    }
}
