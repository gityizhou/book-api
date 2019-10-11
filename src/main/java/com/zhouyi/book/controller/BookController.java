package com.zhouyi.book.controller;


import com.zhouyi.book.bean.Book;
import com.zhouyi.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String list(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        Page<Book> page1 = bookService.findAllByPage(pageable);
        model.addAttribute("page", page1);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book book = bookService.getById(id);
        if (book == null) {
            book = new Book();
        }
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/books/input")
    public String inputPage(Model model) {
        model.addAttribute("book", new Book());
        return "input";
    }

    @PostMapping("/books")
    public String addBook(Book book, RedirectAttributes redirectAttributes) {
        Book b = bookService.saveBook(book);
        if (b == null) {
            redirectAttributes.addFlashAttribute("message", "提交失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "提交成功");
        }
        return "redirect:/admin/books";
    }

    @GetMapping("/books/input/{id}")
    public String inputEditPage(@PathVariable Long id, Model model) {
        Book b = bookService.getById(id);
        model.addAttribute("book", b);
        return "input";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Book b = bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("message", "成功删除: " + b.getName());
        return "redirect:/admin/books";
    }


}
