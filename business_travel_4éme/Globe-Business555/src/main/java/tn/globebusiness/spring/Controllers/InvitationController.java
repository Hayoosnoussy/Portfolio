package tn.globebusiness.spring.Controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.BatchLauncher;
import Exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;
import tn.globebusiness.spring.Entities.Invitation;
import tn.globebusiness.spring.Repositories.CompanyRepository;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.InvitationRepository;
import tn.globebusiness.spring.Services.CompanyServiceImpl;
import tn.globebusiness.spring.Services.EmailSenderService;
import tn.globebusiness.spring.Services.EmployeeServiceImpl;
import tn.globebusiness.spring.Services.InvitationService;

@Slf4j
@RestController
@RequestMapping("/invitation")
public class InvitationController {
	// Injection de dependences

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CompanyServiceImpl companyService;

	@Autowired
	InvitationRepository invitationRepository;

	@Autowired
	InvitationService invitationService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeServiceImpl employeeService;

	@Autowired
	EmailSenderService emailSenderService;

	@GetMapping("/display")
	public List<Invitation> getInvitation() {
		System.out.println("Les invitations sont...");

		List<Invitation> Invitations = new ArrayList<>();
		invitationRepository.findAll().forEach(Invitations::add);

		return Invitations;
	}

	@GetMapping("/nb")
	public Long compter() {
		Long nb = invitationRepository.count();
		return nb;
	}

	@DeleteMapping("/invitationdelete/{id}")
	public Map<String, Boolean> deleteInvitation(@PathVariable(value = "id") Long InvitationId)
			throws ResourceNotFoundException {
		Invitation Invitation = invitationRepository.findById(InvitationId)
				.orElseThrow(() -> new ResourceNotFoundException("Invitation not found  id :: " + InvitationId));

		invitationRepository.delete(Invitation);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	// @Scheduled(fixedDelay=600000)
	// @Scheduled(fixedDelay=600000)
	// @Scheduled(fixedDelay=600000)
	// @Scheduled(fixedDelay=600000)
	// @Scheduled(fixedDelay=600000)

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAllInvitations() {
		System.out.println("Delete All Invitations...");

		invitationRepository.deleteAll();
		log.info("programmé pour tourner toutes les 50 minutes");

		return new ResponseEntity<>("All Invitations have been deleted!", HttpStatus.OK);
	}

	// jdod ici
	@GetMapping("/listparemail")
	public List<Invitation> findByDestinataireEmail(String n) {
		System.out.println("Les invitations sont...");

		List<Invitation> Invitations = new ArrayList<>();
		invitationRepository.findByDestinataireEmail(n);

		return Invitations;
	}

	@GetMapping("/listdone")
	public List<Invitation> getInvitationDone() {
		System.out.println("Les invitations sont...");

		return invitationService.getInvitationDone();
	}

	@GetMapping("/listwait")
	public List<Invitation> getInvitationWaiting() {
		System.out.println("Les invitations sont...");

		return invitationService.getInvitationWaiting();

	}

	@GetMapping("/listpardate")
	public List<Invitation> retrieveInvitationsDate(
			@RequestParam("date1") @DateTimeFormat(pattern = "dd/MM/yyyy") Date date1,
			@RequestParam("date2") @DateTimeFormat(pattern = "dd/MM/yyyy") Date date2) {
		System.out.println("Les invitations sont...");
		return invitationService.retrieveInvitationsDate(date1, date2);

	}

	@GetMapping("/listparcompany/{id}")
	public List<Invitation> getInvitationByCompany(@PathVariable("id") long id) {
		System.out.println("Les invitations sont...");

		return invitationService.getInvitationByCompany(id);
	}

	@GetMapping("/email-sent")
	public Long compterdone() {
		Long nb = invitationRepository.countBySentStatus(true);
		return nb;
	}

	@Autowired
	BatchLauncher batchLauncher;

	/*
	 * Lancer le job d'ajout des lignes stocks à partir de la base de données
	 * manuellement
	 */
	@PostMapping("/add-invitations")
	List<Invitation> addinvitations(@RequestBody List<Invitation> list) {
		return invitationService.addinvitations(list);

	}

}
