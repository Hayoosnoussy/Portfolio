package tn.globebusiness.spring.Services;

import java.util.List;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.EventInvitation;

public interface IEventInvitationService {
	
	public EventInvitation addInvitation(EventInvitation invitation, Employee employee);
	public EventInvitation updateInvitation(EventInvitation invitation);
	public void deleteInvitation(int invitationId);
	public EventInvitation listInvitationByState(String state);
	
	public List<EventInvitation> listAllInvitations();

}
