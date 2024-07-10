package tn.globebusiness.spring.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("select p from Post p where p.employee.id = ?1 order by p.datePost desc ")
    List<Post> findByPosts(Long employeeId);
}