package tn.globebusiness.spring.Services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.globebusiness.spring.Entities.Invitation;

public interface IInvitation {
	
	public void ajouterInvitation(Invitation invitation);
	
	List<Invitation> addinvitations (List<Invitation>list);
    public List<Invitation> getInvitationByCompany(long id);
    public List<Invitation> getMailByStatus();
    public List<Invitation> getInvitationWaiting();
    public List<Invitation> getInvitationDone();  
    public List<Invitation> retrieveInvitationsDate(Date date1,Date date2);

}
