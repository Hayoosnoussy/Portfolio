package tn.globebusiness.spring.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.EventInvitation;

@Repository
public interface EventInvitationRepository extends CrudRepository<EventInvitation, Integer> {

	//@Query("SELECT i FROM event_invitation i WHERE i.state= :state")
	EventInvitation findByState(@Param("state")String state);
	
	@Query("SELECT e.email FROM Employee e WHERE e.id= :id")
	String getEmailByIdForEmployee(@Param("id")Long long1);

}
