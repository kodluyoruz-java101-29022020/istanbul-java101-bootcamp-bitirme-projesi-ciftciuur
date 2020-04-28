package kodluyoruz.graduation.project.model;

import kodluyoruz.graduation.project.enums.BookCategory;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id")
	private Long bookId;

	private String bookName;

	private String bookNote;

	private String bookDescription;

	private String bookPageCount;

	private String publisher;

	private Date publishingYear;

	@Enumerated(EnumType.STRING)
	private BookCategory bookCategory;

	// this column use Soft delete -> deleted=1(deleted) , deleted=0(un deleted)
	private Boolean deleted;

	/*
	 * TODO : Book(m) <--> Author(n)
	 * 
	 * TODO : book_author adında bir tablo iki tablo arasındakı entegrasyonu
	 * sağlıyacak
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "book_id") }, inverseJoinColumns = {
					@JoinColumn(name = "auth_id", referencedColumnName = "author_id") })
	private Author author;

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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
