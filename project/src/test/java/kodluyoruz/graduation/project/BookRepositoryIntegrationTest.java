package kodluyoruz.graduation.project;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import kodluyoruz.graduation.project.dao.BookRepository;
import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.AuthorService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application.properties" })
public class BookRepositoryIntegrationTest {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorService authorService;

	@Test
	@Order(1)
	public void saveBook() {

		Book book = new Book();
		book.setAuthor(authorService.findByAuthorId(1L));
		book.setBookName("repository integration test book name");
		book.setBookNote("repository integration test book note");
		book.setBookCategory(BookCategory.COMPUTER);

		Book resultBook = bookRepository.save(book);

		Assert.assertNotNull(resultBook);

	}
	// TODO : update - delete - searchbyid - searchbyname
}
