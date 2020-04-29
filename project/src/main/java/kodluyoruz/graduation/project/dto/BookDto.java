package kodluyoruz.graduation.project.dto;

import java.util.Date;

import kodluyoruz.graduation.project.enums.BookCategory;

public class BookDto {

	private Long bookId;

	private String bookName;

	private String bookNote;

	private String bookDescription;

	private String bookPageCount;

	private String publisher;

	private Date publishingYear;

	private BookCategory bookCategory;

	private Boolean deleted;

	public Long getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookNote() {
		return bookNote;
	}

	public void setBookNote(String bookNote) {
		this.bookNote = bookNote;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getBookPageCount() {
		return bookPageCount;
	}

	public void setBookPageCount(String bookPageCount) {
		this.bookPageCount = bookPageCount;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishingYear() {
		return publishingYear;
	}

	public void setPublishingYear(Date publishingYear) {
		this.publishingYear = publishingYear;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
