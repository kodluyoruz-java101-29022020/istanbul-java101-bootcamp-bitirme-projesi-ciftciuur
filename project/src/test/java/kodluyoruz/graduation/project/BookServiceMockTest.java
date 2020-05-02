package kodluyoruz.graduation.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import kodluyoruz.graduation.project.dao.BookRepository;
import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.AuthorService;
import kodluyoruz.graduation.project.service.impl.AuthorServiceImpl;
import kodluyoruz.graduation.project.service.impl.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceMockTest {

	@Mock
	private BookRepository bookRepository;
	@InjectMocks
	private BookServiceImpl bookServiceImpl;
	@Autowired
	private AuthorService authorService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveEmployee() {

		Book book = new Book();
		book.setBookName("mock test book name");
		book.setAuthor(authorService.findByAuthorId(1L));
		book.setBookCategory(BookCategory.EDUCATİON);
		book.setBookNote("mock test book note");
		book.setBookPageCount("55");
		book.setPublisher("turkuaz yayım evi");

		Mockito.when(bookRepository.save(book)).thenReturn(book);

		Book book_2 = new Book();
		book_2.setBookName("mock test book name");
		book_2.setAuthor(authorService.findByAuthorId(1L));
		book_2.setBookCategory(BookCategory.EDUCATİON);
		book_2.setBookNote("mock test book note");
		book_2.setBookPageCount("55");
		book_2.setPublisher("turkuaz yayım evi");

		Book resultBook = bookServiceImpl.save(book_2);

		Assert.assertNotNull(resultBook);
	}
}
