package kodluyoruz.graduation.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kodluyoruz.graduation.project.dao.AuthorRepository;
import kodluyoruz.graduation.project.model.Author;
import kodluyoruz.graduation.project.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Override
	@Transactional
	public String save(Author author) {
		if (author != null) {
			authorRepository.save(author);
			return "Yazar kayıt işlemi başarıyla gerçekleştirildi";
		} else {
			return "Yazar boş olamaz";
		}

	}

	@Override
	@Transactional
	public String delete(Long authorId) {
		if (authorId != null) {
			authorRepository.deleteById(authorId);
			return "Silme işlemi başarıyla gerçekleştirildi";
		} else {
			return "Yazar Id'si boş olamaz";
		}

	}

	@Override
	public Author findByAuthorId(Long authId) {
		if (authId != null) {
			return authorRepository.findByAuthorId(authId);
		} else {
			return null;
		}
	}

	@Override
	public List<Author> getAll() {
		return (List<Author>) authorRepository.findAll();
	}

}
