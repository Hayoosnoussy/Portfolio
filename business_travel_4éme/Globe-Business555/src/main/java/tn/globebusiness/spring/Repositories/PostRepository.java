package tn.globebusiness.spring.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Post;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	@Query("select p from Post p where p.employee.id = ?1 order by p.datePost desc ")
	List<Post> HistoryPosts(Long employeeId);
}