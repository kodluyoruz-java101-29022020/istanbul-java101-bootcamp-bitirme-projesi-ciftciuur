package kodluyoruz.graduation.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue
	@Column(name = "author_id")
	private Long authorId;

	private String authorName;

	private String authorSurName;

	private Boolean deleted;

	/*
	 * TODO: author mapped'i aslında booktaki author
	 */
	@ManyToMany(mappedBy = "author")
	private Set<Book> book = new HashSet<>();

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorSurName() {
		return authorSurName;
	}

	public void setAuthorSurName(String authorSurName) {
		this.authorSurName = authorSurName;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
