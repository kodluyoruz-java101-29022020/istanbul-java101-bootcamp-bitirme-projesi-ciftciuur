package kodluyoruz.graduation.project.model;


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

    /*
        TODO: author mapped'i aslında booktaki author
     */
    @OneToOne(mappedBy = "author")
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
