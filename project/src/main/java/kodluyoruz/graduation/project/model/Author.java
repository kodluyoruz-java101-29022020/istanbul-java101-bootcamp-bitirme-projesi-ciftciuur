package kodluyoruz.graduation.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "author")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Author {

	@Id
	@GeneratedValue
	@Column(name = "author_id")
	private Long authorId;

	private String authorName;

	private String authorSurName;

	/*
	 * TODO: author mapped'i aslında booktaki author
	 */
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private Set<Book> book;

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

}
