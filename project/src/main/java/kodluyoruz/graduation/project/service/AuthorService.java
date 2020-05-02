package kodluyoruz.graduation.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kodluyoruz.graduation.project.model.Author;

@Service
public interface AuthorService {

	public String save(Author author);

	public String delete(Long authorId);

	public Author findByAuthorId(Long authId);

	public List<Author> getAll();

}
