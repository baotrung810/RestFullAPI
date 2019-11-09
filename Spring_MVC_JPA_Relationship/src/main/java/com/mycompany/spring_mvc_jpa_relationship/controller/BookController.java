package com.mycompany.spring_mvc_jpa_relationship.controller;

import com.mycompany.spring_mvc_jpa_relationship.entities.BookEntity;
import com.mycompany.spring_mvc_jpa_relationship.service.BookService;
import com.mycompany.spring_mvc_jpa_relationship.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/home", "/"})
    public String home(Model model,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "home";
    }

    @RequestMapping("/update/{bookId}")
    public String viewForm(Model model,
            @PathVariable("bookId") int bookId) {
        BookEntity book = bookService.findBookById(bookId);
        if (book != null && book.getId() > 0) {
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.getCategories());
            model.addAttribute("action", "update-book");
            return "book-form";
        } else {
            return "redirect:/home?status=fail&message=book isn't exist.";
        }
    }

    @RequestMapping(value = "/update-book", method = RequestMethod.POST)
    public String updateBook(Model model,
            @ModelAttribute("book") BookEntity book) {
        bookService.save(book);
        return "redirect:/home";
    }

    @RequestMapping("/add")
    public String viewAddForm(Model model,
            @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("book", new BookEntity());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("action", "add-book");
    model.addAttribute("message", message);
        return "book-form";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addBook(Model model,
            @ModelAttribute("book") BookEntity book) {
        book = bookService.save(book);
        if (book != null && book.getId() > 0) {
            return "redirect:/home";
        } else {
            return "redirect:/add?message= add new book fail.";
        }
    }

    @RequestMapping("/delete/{bookId}")
    public String deleteUser(Model model,
            @PathVariable("bookId") int bookId) {
        boolean check = bookService.deleteBookById(bookId);
        if (check) {
            return "redirect:/home?status=fail&message=delete fail!";
        } else {
            return "redirect:/home?status=ok&message=delete success full!";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model,
            @ModelAttribute("searchText") String searchText) {
        model.addAttribute("books", bookService.search(searchText));
        return "home";
    }
}
