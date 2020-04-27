package kodluyoruz.graduation.project.model;

import kodluyoruz.graduation.project.enums.BookCategory;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_detail")
public class BookDetail {

    @Id
    @Column(name = "book_detail_id")
    private Long bookDetailId;

    private String publisher;

    private Date publishingYear;

    private String bookDescription;

    private String bookPageCount;

    private String isbn;

    private String printing;

    @Enumerated(EnumType.ORDINAL)
    private BookCategory bookCategory;

    //TODO : Book (1) <--> BookDetail(1) *FK
    @OneToOne
    @MapsId
    private Book book;
}
