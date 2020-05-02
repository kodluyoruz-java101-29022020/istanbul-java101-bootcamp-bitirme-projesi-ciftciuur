package kodluyoruz.graduation.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kodluyoruz.graduation.project.controller.BookController;
import kodluyoruz.graduation.project.dao.AuthorRepository;
import kodluyoruz.graduation.project.dao.BookRepository;
import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Author;
import kodluyoruz.graduation.project.model.Book;

@ExtendWith(MockitoExtension.class)
public class BookControllerIntegrationTests {

	@InjectMocks
	BookController bookController;

	@Mock
	BookRepository bookRepository;
	@Mock
	AuthorRepository authorRepository;

	@Test
	public void testAddNewBook() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Book book = new Book();
		book.setBookName("Integration test book name");
		book.setBookNote("Integration test book note");
		book.setBookPageCount("555");
		book.setDeleted(false);
		book.setBookCategory(BookCategory.EDUCATİON);
		book.setPublisher("Turkuaz yayım evi");
		List<Author> authors = new ArrayList<Author>();
		Author author_1 = new Author();
		author_1.setAuthorName("Author 1 name");
		author_1.setAuthorSurName("Author 1 sur name");
		Author author_2 = new Author();
		author_2.setAuthorName("Author 2 name");
		author_2.setAuthorSurName("Author 2 sur name");
		authors.add(author_1);
		authors.add(author_2);
		authorRepository.save(author_1);
		authorRepository.save(author_2);
		bookRepository.save(book);
		System.err.println("ÇALIŞTIIIIIIIIIIIIIIIIII");
	}

}
