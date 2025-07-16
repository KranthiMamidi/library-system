package com.example.library.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library.entity.BorrowRecord;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

//    @Query("SELECT b.book.title, COUNT(b.book.id) FROM BorrowRecord b GROUP BY b.book.id ORDER BY COUNT(b.book.id) DESC")
//    List<Object[]> findMostBorrowedBooks();

	@Query("SELECT b.title, COUNT(br.id) FROM BorrowRecord br JOIN br.book b GROUP BY b.id, b.title ORDER BY COUNT(br.id) DESC")
	List<Object[]> findMostBorrowedBooks();

	@Query("SELECT br FROM BorrowRecord br WHERE br.returnDate IS NULL AND br.borrowDate < :cutoffDate")
	List<BorrowRecord> findOverdueBooks(@Param("cutoffDate") LocalDate cutoffDate);

}
