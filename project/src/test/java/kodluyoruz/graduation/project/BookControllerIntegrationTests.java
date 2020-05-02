package kodluyoruz.graduation.project;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.AuthorService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application.properties" })
public class BookControllerIntegrationTests {
	@LocalServerPort
	private int tomcatPortNo;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private AuthorService authorService;

	/*
	 * TODO : test tarafı için yeni bir data source gerekli
	 */

	@Test
	@Order(1)
	public void findBookById() {
		// Id'si 2 olan kitabın veri tabanında kontrolunu yapar rest api testi

		String url = bookRestRootUrl() + "/search/id?bookId=2";

		ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);

		Book book = response.getBody();

		Assert.assertTrue(2 == book.getBookId());

	}

	@Test
	@Order(2)
	public void saveBook() {
		// manuel olusturulmus kitap nesnesıne 1'nolu yazar setlenerek verıtabanına
		// işlemini yapan api testi yazıldı
		String url = bookRestRootUrl() + "/save";

		Book book = new Book();
		book.setAuthor(authorService.findByAuthorId(1L));
		book.setBookName("controller integration test book name");
		book.setBookNote("controller integration test book note");
		book.setBookCategory(BookCategory.ACADEMIC);

		ResponseEntity<Book> response = restTemplate.postForEntity(url, book, Book.class);

		Assert.assertTrue(response.getBody().getBookId() != null);

	}

	@Test
	@Order(3)
	public void searchByBookName() {
		// kitap adına göre veritabanında arama yapan rest api testi yazıldı

		String url = bookRestRootUrl() + "/search?bookName=test";

		ResponseEntity<Book[]> response = restTemplate.getForEntity(url, Book[].class);

		Assert.assertTrue(response.getBody().length > 0);

	}

	// TODO delete api integration test added

	private String bookRestRootUrl() {
		return "http://localhost:" + tomcatPortNo + "/api/book/";
	}
}
