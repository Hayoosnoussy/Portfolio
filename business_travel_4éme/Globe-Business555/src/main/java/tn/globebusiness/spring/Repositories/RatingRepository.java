	package tn.globebusiness.spring.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {
	
	@Query("select r from Rating r where r.employee.id = 1 and r.event.eventId=:eventId")
	Rating listEmployeeRatingForEvent(@Param("eventId") int eventId);
	
	//@Query("select COUNT(r) from Rating r where r.employee.id = 1 and r.ratingId =:ratingId")
	//int numberOfRating(@Param("ratingId") int ratingId);

}
