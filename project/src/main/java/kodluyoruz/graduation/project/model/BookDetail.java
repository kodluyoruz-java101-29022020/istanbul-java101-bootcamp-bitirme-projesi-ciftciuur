package kodluyoruz.graduation.project.model;

public class BookDetail {

    private Long bookDetailId;

    private String bookDescription;

    private String bookPageCount;

    private String isbn;

    private String printing;


    //TODO : Book (1) <--> BookDetail(1) *FK
    private Book book;
}
