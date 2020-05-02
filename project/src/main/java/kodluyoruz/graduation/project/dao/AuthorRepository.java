package kodluyoruz.graduation.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kodluyoruz.graduation.project.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

	// This query find author for update author
	@Query("SELECT auth FROM Author auth WHERE auth.authorId=:authId")
	public Author findByAuthorId(@Param("authId") Long authId);

	// criteria : author_name -> search authors
	@Query("SELECT auth FROM Author auth WHERE auth.authorName LIKE %:authName% ")
	public List<Author> findByAuthorNameLike(@Param("authName") String authName);

	// criteria : author_sur_name -> search authors
	@Query("SELECT auth FROM Author auth WHERE auth.authorSurName LIKE %:authSurName% ")
	public List<Author> findByAuthorSurNameLike(@Param("authSurName") String authSurName);

}
