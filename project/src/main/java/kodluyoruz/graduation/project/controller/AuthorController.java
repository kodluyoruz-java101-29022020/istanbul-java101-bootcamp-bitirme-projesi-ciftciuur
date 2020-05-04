package kodluyoruz.graduation.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodluyoruz.graduation.project.model.Author;
import kodluyoruz.graduation.project.service.AuthorService;

@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@RequestMapping(value = "/api/author/save", method = RequestMethod.POST)
	public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
		Author auth = authorService.save(author);
		if (auth != null) {
			return new ResponseEntity<Author>(auth, HttpStatus.OK);
		} else {
			return new ResponseEntity<Author>(auth, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/api/author/update", method = RequestMethod.PUT)
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
		Author auth = authorService.save(author);
		if (auth != null) {
			return new ResponseEntity<Author>(auth, HttpStatus.OK);
		} else {
			return new ResponseEntity<Author>(auth, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/api/author/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAuthor(@RequestParam Long authorId) {
		return new ResponseEntity<String>(authorService.delete(authorId), HttpStatus.OK);
	}

}
