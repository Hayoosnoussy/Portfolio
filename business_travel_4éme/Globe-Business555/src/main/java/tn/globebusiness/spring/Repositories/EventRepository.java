package tn.globebusiness.spring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Category;
import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Entities.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

	Event findByCategory(String category);

	@Query("select e from Event e order by e.startDate")
	List<Event> findAllEvents();

	@Query("Select e from Event e join e.category c where c= :category")
	List<Event> findByCategory(@Param("category") Category category);

	@Query("Select e from Event e where e.category=: category")
	List<Event> findByCategoryForCompany(@Param("category") Category category);

	@Query("Select e.company from Employee e where e.id= :employeeId")
	Company findByCategoryForEmployee(@Param("employeeId") Long employeeId);

	@Query("select COUNT(e) from Event e where e.category =: category")
	int categoryStats(@Param("category") Category cateory);

	long count();

}
