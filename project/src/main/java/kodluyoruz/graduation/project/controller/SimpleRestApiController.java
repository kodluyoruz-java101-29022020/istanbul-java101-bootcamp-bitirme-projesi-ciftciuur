package kodluyoruz.graduation.project.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodluyoruz.graduation.project.model.Author;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.AuthorService;
import kodluyoruz.graduation.project.service.BookService;

@RestController
public class SimpleRestApiController {
	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	/*
	 * bu fonksiyon çağrılan post istegini karsılar ve yenı kayıt ekler static
	 * olarak
	 */
	@RequestMapping(value = "/api/crud/save", method = RequestMethod.POST)
	public void saveBook() {
		Book tempBook = new Book();
		tempBook.setBookName("java kitabı");
		tempBook.setBookNote("java kitap notu ");
		tempBook.setBookPageCount("600");
		Author tempAuthors = new Author();
		tempAuthors.setAuthorName("test yazar adı 1");

		Author tempAuthors2 = new Author();
		tempAuthors2.setAuthorName("test yazar adı 2");
		authorService.saveAuthor(tempAuthors);
		tempBook.setAuthor(new HashSet<>(Arrays.asList(tempAuthors, tempAuthors2)));
		bookService.saveBook(tempBook);
		System.err.println("kayıt yapıldı");
	}

	@RequestMapping(value = "/api/crud/save", method = RequestMethod.POST)
	public void saveBook(Book book, Set<Author> author) {

		book.setAuthor(author);
		bookService.saveBook(book);
		System.err.println("kayıt yapıldı");
	}

	/*
	 * bu fonksiyon dışarıdan gelen kıtapid'sini veritabanında sorgular ve kaydı
	 * geri döner
	 */
	@RequestMapping(value = "/api/get/book", method = RequestMethod.GET)
	public Book getBookById(@RequestParam Long id) {

		Book book = bookService.findByBookId(id);
		System.err.println(book.toString());
		System.err.println("--");
		System.err.println(book.getAuthor().size());

		return null;
	}

	/*
	 * bu fonksiyon dışarıdan parametre olarak verılen bookid'sini veri tabanından
	 * sıler silinen tablolar(book tablosu ve book_author tablosu)
	 */
	@RequestMapping(value = "/api/delete/book", method = RequestMethod.DELETE)
	public String deleteBook(@RequestParam Long id) {
		bookService.hardDeleteBook(id);
		return "silme başarılı";
	}

}
