package kodluyoruz.graduation.project.model;

import kodluyoruz.graduation.project.enums.BookCategory;

import java.util.List;

public class Book {

    private Long bookId;

    private String bookName;

    private String bookNote;

    private BookCategory bookCategory;

    //TODO : Book (1) <--> BookDetail(1) *FK
    private BookDetail bookDetail;
    

}
