package kodluyoruz.graduation.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kodluyoruz.graduation.project.dao.BookRepository;
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
		// TODO Auto-generated method stub

	}
}
