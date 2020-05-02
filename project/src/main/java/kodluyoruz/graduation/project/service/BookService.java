package kodluyoruz.graduation.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;

@Service
public interface BookService {

	public Book save(Book book);

	public String delete(Long bookId);

	public List<Book> searchBookCategory(BookCategory category);

	public List<Book> findByBookName(String bookName);

	public List<BookCategory> getAllBookCategories();

	public Optional<Book> findByBookId(Long bookId);

	public List<Book> findByBookCategory(BookCategory bookCategory);

	public List<Book> getAll();

}
