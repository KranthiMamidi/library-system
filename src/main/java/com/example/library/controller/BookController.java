package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/all-books")
	public List<Book> getAllBooks() {
		return bookService.findAll();
	}

	@PutMapping("update/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
		return bookService.updateBook(id, book);
	}

}
