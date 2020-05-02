package kodluyoruz.graduation.project.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import kodluyoruz.graduation.project.enums.BookCategory;

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

	// this column use Soft delete -> true = deleted - false = undeleted
	private Boolean deleted;

	/*
	 * TODO : Book(m) <--> Author(n)
	 * 
	 * TODO : book_author adında bir tablo iki tablo arasındakı entegrasyonu
	 * sağlıyacak
	 */
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "book_id") }, inverseJoinColumns = {
					@JoinColumn(name = "auth_id", referencedColumnName = "author_id") })
	private Set<Author> author = new HashSet<>();

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Set<Author> getAuthor() {
		return author;
	}

	public void setAuthor(Set<Author> author) {
		this.author = author;
	}

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

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookNote=" + bookNote + ", bookDescription="
				+ bookDescription + ", bookPageCount=" + bookPageCount + ", publisher=" + publisher
				+ ", publishingYear=" + publishingYear + ", bookCategory=" + bookCategory + ", deleted=" + deleted
				+ ", author=" + author + "]";
	}

}
