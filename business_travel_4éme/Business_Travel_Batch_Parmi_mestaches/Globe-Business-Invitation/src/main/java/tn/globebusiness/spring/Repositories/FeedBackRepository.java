package tn.globebusiness.spring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.FeedBack;
import tn.globebusiness.spring.Entities.Travel;

@Repository
public interface FeedBackRepository extends CrudRepository<FeedBack,Long> {
	@Query("select f from FeedBack f where f.employee=:employee")
	List<FeedBack> displayAllUserFeedBack(@Param("employee") Employee employee);
	@Query("select f from FeedBack f where f.travel=:travel")
	List<FeedBack> displayAllTravelFeedBack(@Param("travel") Travel travel);
}
