package tn.globebusiness.spring.Controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.EventInvitation;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.EventInvitationRepository;
import tn.globebusiness.spring.Services.IEventInvitationService;

@RestController
public class EventInvitationController {

	@Autowired
	IEventInvitationService eventInvitationService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	EventInvitationRepository eventInvitationRepository;

	List<Employee> employees = new ArrayList<Employee>();

	@PostMapping("AddEventInvitation")
	public void addEvent(@RequestBody EventInvitation invitation, @PathParam("eventId") int eventId) {

		employees.add(employeeRepository.findById((long) 1).orElse(new Employee()));
		employees.add(employeeRepository.findById((long) 2).orElse(new Employee()));
		//employees.add(employeeRepository.findById((long) 3).orElse(new Employee()));
		List<String> emails = new ArrayList<String>();
		for (int i = 0; i < employees.size(); i++) {
			emails.add(eventInvitationRepository.getEmailByIdForEmployee(employees.get(i).getId()));
		}

		for (int i = 0; i < emails.size(); i++) {
			try {
				sendEmailWithAttachment("ayoubdhamer5@gmail.com", "This is an email invitaion to join an event",
						"This email has attachment", "D:\\ayoub\\Downloads\\img.png");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

		employees.forEach((employee) -> {
			eventInvitationService.addInvitation(invitation, employee, eventId);
		});
	}

	@PutMapping("AcceptEventInvitation/{idEventInvitation}")
	public EventInvitation acceptEventInvitation(@PathVariable("idEventInvitation") int invitationId) {
		return eventInvitationService.acceptInvitation(invitationId);
	}

	/*
	 * @PutMapping("RefuseEventInvitation/{idEventInvitation}") public void
	 * refuseEventInvitation(@PathVariable("idEventInvitation") int
	 * invitationId,
	 * 
	 * @RequestBody EventInvitation invitation) {
	 * eventInvitationService.refuseInvitation(invitationId, invitation); }
	 */

	/*
	 * @GetMapping("ListEventInvitation/{eventId}") public Event
	 * listEvent(@PathVariable("eventId") Integer eventId) { return
	 * eventInvitationService.listEvent(eventId); }
	 */

	@DeleteMapping("deleteEventInvitation/{invitationId}")
	public boolean deleteEventInvitation(@PathVariable("invitationId") int invitationId) {
		eventInvitationService.deleteInvitation(invitationId);
		return true;
	}

	@GetMapping("ListAllEventInvitations")
	public List<EventInvitation> listAllInvitatios() {
		return eventInvitationService.listAllInvitations();
	}

	@GetMapping("ListEventInvitationsByEmployee")
	public List<EventInvitation> ListEventInvitationsByEmployee() {
		return eventInvitationService.listEventInvitationsByEmployee();
	}

	public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment)
			throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		String url = "http://localhost:8082/GlobeBusiness/delateEvent/5";

		mimeMessageHelper.setFrom("ayoubdhamer5@gmail.com");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);

		FileSystemResource fileSystem = new FileSystemResource(new File(attachment));

		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);

		mailSender.send(mimeMessage);
		System.out.println("Mail Send...");

	}

	public void sendSimpleEmail(String toEmail, String body, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("ayoubdhamer5@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);
		System.out.println("Mail Send...");
	}

}
