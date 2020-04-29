package kodluyoruz.graduation.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	@Query("UPDATE Book book set book.deleted=1 WHERE book.bookId=:bookId")
	public void deleteSoftBook(@Param("bookId") Long bookId);

	// This query find book for update book
	@Query("SELECT book FROM Book book WHERE book.bookId=:bookId AND book.deleted=0")
	public Book findByBookId(@Param("bookId") Long bookId);

	@Query("SELECT book FROM Book book WHERE book.bookCategory=:bookCategory AND book.deleted=0")
	public List<Book> findByBookCategory(@Param("bookCategory") BookCategory bookCategory);

	@Query("SELECT book FROM Book book WHERE book.deleted=0 AND  book.bookName LIKE %:bookName% ")
	public List<Book> findByBookName(@Param("bookName") String bookName);

	@Query("SELECT book FROM Book book WHERE book.deleted=0 ORDER BY book.publishingYear ASC")
	public List<Book> sortBooksByRelaseDate();

	@Query("SELECT book FROM Book book WHERE book.deleted=0")
	public List<Book> getAllUnDeletedBooks();

	@Query("SELECT book FROM Book book WHERE book.deleted=1")
	public List<Book> getAllDeleted();

}
