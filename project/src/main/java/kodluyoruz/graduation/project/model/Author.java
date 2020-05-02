package kodluyoruz.graduation.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@ManyToOne(fetch = FetchType.LAZY)
	private Book book;

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
