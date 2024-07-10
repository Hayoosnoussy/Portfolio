package tn.globebusiness.spring.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
	public List<Invitation> findByDestinataireEmail(String n);

	public Page<Invitation> findByDestinataireEmail(String n, Pageable pageable);

	@Query("SELECT i FROM Invitation i WHERE i.date BETWEEN :date1 and :date2")
	List<Invitation> retrieveInvitationsDate(@Param("date1") Date date1, @Param("date2") Date date2);

	@Query("SELECT i FROM Invitation i WHERE i.sentStatus=0")
	public List<Invitation> getInvitationWaiting();

	@Query("SELECT i FROM Invitation i WHERE i.sentStatus=1")
	public List<Invitation> getInvitationDone();

	@Query("SELECT i FROM Invitation i where company_id=:id")
	public List<Invitation> getInvitationByCompany(@Param("id") long id);

	@Query("SELECT destinataireEmail FROM Invitation i where sentStatus=0")
	public List<Invitation> getMailByStatus();

	long countBySentStatus(boolean sentStatus);

}