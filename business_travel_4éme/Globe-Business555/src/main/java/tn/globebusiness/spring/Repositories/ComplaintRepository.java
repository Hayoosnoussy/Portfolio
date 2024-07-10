package tn.globebusiness.spring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Complaint;
import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Travel;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
	@Query("select c from Complaint c where c.title=:title")
	List<Complaint> findByTitle(@Param("title") String title);

	@Query("select c from Complaint c where c.employee=:employee")
	List<Complaint> displayAllUserComplaints(@Param("employee") Employee employee);

	@Query("select c from Complaint c where c.travel=:travel")
	List<Complaint> displayAllTravelComplaints(@Param("travel") Travel travel);
}
