package kodluyoruz.graduation.project.service.impl;

import kodluyoruz.graduation.project.dao.AuthorRepository;
import kodluyoruz.graduation.project.model.Author;
import kodluyoruz.graduation.project.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// TODO Auto-generated method stub

	}
}
