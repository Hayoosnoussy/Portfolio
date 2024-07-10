package tn.globebusiness.spring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.EventInvitation;

@Repository
public interface EventInvitationRepository extends CrudRepository<EventInvitation, Integer> {

	// @Query("SELECT i FROM event_invitation i WHERE i.state= :state")
	EventInvitation findByState(@Param("state") String state);

	@Query("SELECT e.email FROM Employee e WHERE e.id=:id")
	String getEmailByIdForEmployee(@Param("id") Long long1);

	@Query("SELECT e FROM EventInvitation e WHERE e.employee.id=:employeeId")
	List<EventInvitation> getEventInvitationsByEmployee(@Param("employeeId") Long employeeId);

	@Modifying
	@Query("update EventInvitation e set e.state= 	Accepted where e.invitationId =:invitationId")
	void acceptInvitation(@Param("invitationId")int invitationId);

}
