package kodluyoruz.graduation.project.dao;

import kodluyoruz.graduation.project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
