package kodluyoruz.graduation.project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kodluyoruz.graduation.project.annotation.RuntimeAspect;
import kodluyoruz.graduation.project.dao.BookRepository;
import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	@Override
	@Transactional
	public void saveBook(Book book) {
		if (book != null) {
			bookRepository.save(book);
		}

	}

	@Override
	@Transactional
	public void hardDeleteBook(Long bookId) {
		if (bookId != null) {
			bookRepository.deleteById(bookId);
		}

	}

	@Override
	@Transactional
	public void updateBook(Long bookId, Book book) {
		if (bookId != null) {
			Book tempBook = bookRepository.findByBookId(bookId);
			tempBook = book;
			bookRepository.save(tempBook);
		}

	}

	@Override
	public void softDeleteBook(Long bookId) {
		if (bookId != null) {
			bookRepository.deleteSoftBook(bookId);
		}
	}

	@Override
	public List<Book> searchBookCategory(BookCategory category) {
		if (category != null) {
			return bookRepository.findByBookCategory(category);
		} else {
			return new ArrayList<Book>();
		}

	}

	@Override
	public List<Book> searchBookName(String bookName) {
		if (bookName != null) {
			return bookRepository.findByBookName(bookName);
		} else {
			return new ArrayList<Book>();
		}

	}

	@RuntimeAspect(active = true)
	@Override
	public List<Book> getAllUnDeletedBooks() {
		return bookRepository.getAllUnDeletedBooks();

	}

	@Override
	public List<BookCategory> getAllBookCategories() {
		return Arrays.asList(BookCategory.values());
	}
}
