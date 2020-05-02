package kodluyoruz.graduation.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kodluyoruz.graduation.project.model.Author;

@Service
public interface AuthorService {

	public void saveAuthor(Author author);

	public void hardDeleteAuthor(Long authorId);

	public void softDeleteAuthor(Long authorId);

	public void updateAuthor(Long authorId, Author author);

	public List<Author> getAllUnDeletedAuthors();
	
	public Author getAuthorDetail();

	
}
