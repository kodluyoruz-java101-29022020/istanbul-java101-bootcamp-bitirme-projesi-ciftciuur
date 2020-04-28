package kodluyoruz.graduation.project.service;

import org.springframework.stereotype.Service;

import kodluyoruz.graduation.project.model.Book;

@Service
public interface BookService {

	public void saveBook(Book book);

	public void hardDeleteBook(Long bookId);

	public void softDeleteBook(Long bookId);

	public void updateBook(Long bookId, Book book);

}
