package kodluyoruz.graduation.project.model;

import kodluyoruz.graduation.project.enums.BookCategory;

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
    //TODO : Book (1) <--> BookDetail(1) *FK
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private BookDetail bookDetail;

    //TODO : Book(m) <--> Author(n)
    /*
        TODO :  book_author adında bir tablo iki tablo arasındakı entegrasyonu sağlıyacak
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns =
                    {@JoinColumn(name = "book_id", referencedColumnName = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "auth_id", referencedColumnName = "author_id")}
    )
    private Author author;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
