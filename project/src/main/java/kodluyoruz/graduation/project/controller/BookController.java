package kodluyoruz.graduation.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.BookService;

@RestController
public class BookController {
	// TODO : Crud Controller

	/*
	 * TODO : api's kitap kaydetme(post) ,
	 * 
	 * kitap güncelleme(put)
	 * 
	 * kitap silme(delete)
	 * 
	 * kitap ara(get)
	 */

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/api/book/save", method = RequestMethod.POST)
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		return new ResponseEntity<Book>(bookService.save(book), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/book/update", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		return new ResponseEntity<Book>(bookService.save(book), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/book/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBook(@RequestParam Long bookId) {
		return new ResponseEntity<String>(bookService.delete(bookId), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/book/search", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> searchBookByName(@RequestParam String bookName) {
		return new ResponseEntity<List<Book>>(bookService.findByBookName(bookName), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/book/search/id", method = RequestMethod.GET)
	public Optional<Book> searchBookById(@RequestParam Long bookId) {
		return bookService.findByBookId(bookId);
	}

	@RequestMapping(value = "/api/book/search/category", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> searchBookByCategory(@RequestParam BookCategory bookCategory) {
		return new ResponseEntity<List<Book>>(bookService.searchBookCategory(bookCategory), HttpStatus.OK);
	}

}
