package kodluyoruz.graduation.project.dto;

import java.util.List;

import kodluyoruz.graduation.project.enums.BookCategory;

public class BookSearchDto {
	// kitap adı
	private String bookName;
	// kitap sayfa sayısı
	private String bookPageCount;
	// kitap isbn
	private String isbn;
	// kitap baskı detayı
	private String printing;
	// yazar adı veya adları
	private List<String> authorNames;
	// kategori adı
	private BookCategory bookCategory;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPageCount() {
		return bookPageCount;
	}

	public void setBookPageCount(String bookPageCount) {
		this.bookPageCount = bookPageCount;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPrinting() {
		return printing;
	}

	public void setPrinting(String printing) {
		this.printing = printing;
	}

	public List<String> getAuthorNames() {
		return authorNames;
	}

	public void setAuthorNames(List<String> authorNames) {
		this.authorNames = authorNames;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

}
