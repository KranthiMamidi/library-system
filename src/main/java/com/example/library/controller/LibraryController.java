package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.BorrowRecord;
import com.example.library.service.LibraryService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	@GetMapping("/ping")
	public String ping() {
		return "Library Controller Active";
	}

	@PostMapping("/borrow")
	public ResponseEntity<BorrowRecord> borrowBook(@RequestParam Long bookId, @RequestParam Long memberId) {
		return ResponseEntity.ok(libraryService.borrowBook(bookId, memberId));
	}


	@PostMapping("/return")
	public ResponseEntity<BorrowRecord> returnBook(@RequestParam Long recordId) {
		return ResponseEntity.ok(libraryService.returnBook(recordId));
	}

	@GetMapping("/reports/most-borrowed")
	public ResponseEntity<List<Object[]>> mostBorrowedBooks() {
		return ResponseEntity.ok(libraryService.getMostBorrowedBooks());
	}

	@GetMapping("/reports/overdue")
	public ResponseEntity<List<BorrowRecord>> overdueBooks() {
		return ResponseEntity.ok(libraryService.getOverdueBooks());
	}

}
