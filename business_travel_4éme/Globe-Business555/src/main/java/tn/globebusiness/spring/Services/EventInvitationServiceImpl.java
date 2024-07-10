package tn.globebusiness.spring.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Event;
import tn.globebusiness.spring.Entities.EventInvitation;
import tn.globebusiness.spring.Entities.State;
import tn.globebusiness.spring.Repositories.CompanyRepository;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.EventInvitationRepository;
import tn.globebusiness.spring.Repositories.EventRepository;

@Service
public class EventInvitationServiceImpl implements IEventInvitationService {

	@Autowired
	EventInvitationRepository invitationRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	private State Accepted;
	private State Refused;

	@Override
	public EventInvitation addInvitation(EventInvitation invitation, Employee employee, int eventId) {
		//Employee employee1 = employeeRepository.findById((long) 1).orElse(new Employee());
		
		invitation.setEmployee(employee);
		invitation.setCompany(companyRepository.findById((long) 1).get());
		invitation.setEvent(eventRepository.findById(eventId).get());
		return invitationRepository.save(invitation);
	}

	@Override
	public EventInvitation acceptInvitation(int invitationId) {
		EventInvitation oldInvitation = invitationRepository.findById(invitationId).orElse(new EventInvitation());
		oldInvitation.setDate(new Date());
		
		invitationRepository.save(oldInvitation);
		//invitationRepository.acceptInvitation(invitationId);
		
		return invitationRepository.findById(invitationId).get();
	}
/*
	@Override
	public void refuseInvitation(int invitationId) {

		EventInvitation oldInvitation = invitationRepository.findById(invitationId).orElse(new EventInvitation());
		oldInvitation.setState(newInvitation.getState());

		invitationRepository.save(oldInvitation);
		/*
		 * EventInvitation invitation =
		 * invitationRepository.findById(invitationId).orElse(new
		 * EventInvitation()); EventInvitation eventInvitation =
		 * invitationRepository.findById(invitation.getInvitationId()).get();
		 * eventInvitation.setState(Refused);
		 * invitationRepository.save(eventInvitation);
		 *//*
	}*/

	@Override
	public void deleteInvitation(int invitationId) {
		invitationRepository.delete(invitationRepository.findById(invitationId).get());
	}

	@Override
	public EventInvitation listInvitationByState(String state) {
		EventInvitation invitation = invitationRepository.findByState(state);
		if (invitation != null)
			return invitation;
		return null;
	}

	@Override
	public List<EventInvitation> listAllInvitations() {
		return (List<EventInvitation>) invitationRepository.findAll();
	}

	@Override
	public List<EventInvitation> listEventInvitationsByEmployee() {
		Long employeeId = (long) 1;
		return invitationRepository.getEventInvitationsByEmployee(employeeId);
	}

	@Override
	public EventInvitation refuseInvitation(int invitationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
