package tn.globebusiness.spring.Services;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.EventInvitation;
import tn.globebusiness.spring.Repositories.EventInvitationRepository;

@Service
public class EventInvitationServiceImpl implements IEventInvitationService {
	
	@Autowired
	EventInvitationRepository invitationRepository;

	@Override
	public EventInvitation addInvitation(EventInvitation invitation, Employee employee) {
		String email = invitationRepository.getEmailByIdForEmployee(employee.getId());
		
		/*try {
			sendEmailWithAttachment("ayoubdhamer5@gmail.com",
			"This is an email invitaion to join an event",
			"This email has attachment",
			"D:\\ayoub\\Downloads\\img.png");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*sendSimpleEmail("ayoubdhamer5@gmail.com",
		"This is the Email Body",
		"This is the email Subject");*/
		
		invitation.setEmployee(employee);
		return invitationRepository.save(invitation);
	}

	@Override
	public EventInvitation updateInvitation(EventInvitation invitation) {
		return invitationRepository.save(invitation);
	}

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
	
}
