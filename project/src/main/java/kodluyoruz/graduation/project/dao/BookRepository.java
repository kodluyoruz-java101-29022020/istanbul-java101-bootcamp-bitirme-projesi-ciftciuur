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

	// This query find book for update book
	@Query("SELECT book FROM Book book WHERE book.bookId=:bookId")
	public Book findByBookId(@Param("bookId") Long bookId);

	@Query("SELECT book FROM Book book WHERE book.bookCategory=:bookCategory")
	public List<Book> findByBookCategory(@Param("bookCategory") BookCategory bookCategory);

	// TODO like sorgusu calısmıyor !!!
	@Query("SELECT book FROM Book book WHERE  book.bookName LIKE '%:bookName%'")
	public List<Book> findByBookName(@Param("bookName") String bookName);

	@Query("SELECT book FROM Book book  ORDER BY book.publishingYear ASC")
	public List<Book> sortBooksByRelaseDate();

}
