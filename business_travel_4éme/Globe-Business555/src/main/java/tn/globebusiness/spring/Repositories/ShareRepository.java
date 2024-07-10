package tn.globebusiness.spring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Share;

@Repository
public interface ShareRepository extends CrudRepository<Share, Long> {
	@Query("select s from Share s where  s.employee2=:employee2")
	List<Share> myFriendPost(@Param("employee2") Employee employee2);
}
