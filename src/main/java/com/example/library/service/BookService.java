package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book updateBook(Long id, Book book) {
		Book oBook = bookRepository.findById(id).get();
		if (book.getTitle() != null) {
			oBook.setTitle(book.getTitle());
		}
		if (book.getAuthor() != null) {
			oBook.setAuthor(book.getAuthor());
		}
		bookRepository.save(oBook);
		return oBook;
	}

}
