package com.example.library.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.entity.BorrowRecord;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.MemberRepository;

@Service
public class LibraryService {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private BorrowRecordRepository borrowRepo;

	public BorrowRecord borrowBook(Long bookId, Long memberId) {
		Book book = bookRepo.findById(bookId).orElseThrow();
		if (!book.isAvailable()) {
			throw new RuntimeException("Book not available");
		}
		book.setAvailable(false);
		bookRepo.save(book);

		BorrowRecord record = new BorrowRecord();
		record.setBook(book);
		record.setMember(memberRepo.findById(memberId).orElseThrow());
		record.setBorrowDate(LocalDate.now());

		return borrowRepo.save(record);
	}

	public BorrowRecord returnBook(Long recordId) {
		BorrowRecord record = borrowRepo.findById(recordId).orElseThrow();
		record.setReturnDate(LocalDate.now());

		Book book = record.getBook();
		book.setAvailable(true);
		bookRepo.save(book);

		return borrowRepo.save(record);
	}

	public List<Object[]> getMostBorrowedBooks() {
		return borrowRepo.findMostBorrowedBooks();
	}

	public List<BorrowRecord> getOverdueBooks() {
		return borrowRepo.findOverdueBooks(LocalDate.now().minusDays(14)); // overdue > 2 weeks
	}

}
