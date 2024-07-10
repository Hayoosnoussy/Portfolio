package tn.globebusiness.spring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Comment;
@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {

}
