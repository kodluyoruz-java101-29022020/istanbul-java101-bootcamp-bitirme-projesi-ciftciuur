package kodluyoruz.graduation.project.service;

import org.springframework.stereotype.Service;

import kodluyoruz.graduation.project.model.Book;

@Service
public interface BookService {

	public void save(Book book);
}
