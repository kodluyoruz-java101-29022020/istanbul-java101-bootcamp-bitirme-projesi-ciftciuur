package kodluyoruz.graduation.project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kodluyoruz.graduation.project.annotation.RuntimeAspect;
import kodluyoruz.graduation.project.dao.BookRepository;
import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	@Transactional
	public String save(Book book) {
		if (book != null) {
			bookRepository.save(book);
			return "Güncelle işlemi başarılı";

		} else {
			return "Kitap objesi boş olamaz";
		}
	}

	@Override
	@Transactional
	public String delete(Long bookId) {
		if (bookId != null) {
			Book book = bookRepository.findByBookId(bookId);
			if (book != null) {
				bookRepository.deleteById(bookId);
				return "Kayıt silindi";
			} else {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(bookId);
				stringBuilder.append(" no'lu kayıt veri tabanında mevcut değil");
				return stringBuilder.toString();
			}
		} else {
			return "Kitap ID'si boş olamaz";
		}

	}

	@Override
	public List<Book> searchBookCategory(BookCategory category) {
		if (category != null) {
			return bookRepository.findByBookCategory(category);
		} else {
			return new ArrayList<Book>();
		}

	}

	@Override
	public List<Book> findByBookName(String bookName) {
		if (bookName != null) {
			return bookRepository.findByBookName(bookName);
		} else {
			return new ArrayList<Book>();
		}

	}

	@Override
	public List<BookCategory> getAllBookCategories() {
		return Arrays.asList(BookCategory.values());
	}

	@Override
	public Book findByBookId(Long bookId) {
		return bookRepository.findByBookId(bookId);
	}

	@Override
	public List<Book> findByBookCategory(BookCategory bookCategory) {
		if (bookCategory != null) {
			return bookRepository.findByBookCategory(bookCategory);
		} else {
			return new ArrayList<Book>();
		}
	}

	@RuntimeAspect(active = true)
	@Override
	public List<Book> getAll() {
		return (List<Book>) bookRepository.findAll();
	}
}
