package kodluyoruz.graduation.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;

@Service
public interface BookService {

	public void saveBook(Book book);

	public void hardDeleteBook(Long bookId);

	public void softDeleteBook(Long bookId);

	public void updateBook(Long bookId, Book book);

	public List<Book> searchBookCategory(BookCategory category);

	public List<Book> searchBookName(String bookName);

	public List<Book> getAllUnDeletedBooks();

}
