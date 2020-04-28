package kodluyoruz.graduation.project.service;

import org.springframework.stereotype.Service;

import kodluyoruz.graduation.project.model.Author;

@Service
public interface AuthorService {

	public void saveAuthor(Author author);

	public void hardDeleteAuthor(Long authorId);

	public void softDeleteAuthor(Long authorId);

	public void updateAuthor(Long authorId, Author author);
}
