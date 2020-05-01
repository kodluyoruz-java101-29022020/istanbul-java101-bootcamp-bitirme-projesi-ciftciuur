package kodluyoruz.graduation.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kodluyoruz.graduation.project.annotation.RuntimeAspect;
import kodluyoruz.graduation.project.dao.AuthorRepository;
import kodluyoruz.graduation.project.model.Author;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Override
	@Transactional
	public void saveAuthor(Author author) {
		if (author != null) {
			authorRepository.save(author);
		}

	}

	@Override
	@Transactional
	public void hardDeleteAuthor(Long authorId) {
		if (authorId != null) {
			authorRepository.deleteById(authorId);
		}

	}

	@Override
	@Transactional
	public void updateAuthor(Long authorId, Author author) {
		if (authorId != null) {
			Author tempAuthor = authorRepository.findByAuthorId(authorId);
			tempAuthor = author;
			authorRepository.save(tempAuthor);
		}

	}

	@Override
	public void softDeleteAuthor(Long authorId) {
		if (authorId != null) {
			authorRepository.deleteSoftAuthor(authorId);
		}

	}

	@RuntimeAspect(active = true)
	@Override
	public List<Author> getAllUnDeletedAuthors() {
		return authorRepository.getAllUnDeletedAuthors();

	}

	@Override
	public Author getAuthorDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
