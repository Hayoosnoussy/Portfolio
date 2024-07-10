package tn.globebusiness.spring.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Exception.ResourceNotFoundException;
import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Repositories.CompanyRepository;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.InvitationRepository;
import tn.globebusiness.spring.Services.CompanyService;
import tn.globebusiness.spring.Services.EmployeeService;
import tn.globebusiness.spring.Services.InvitationService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	// Injection de dependences
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	InvitationService invitationService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	//CRUD SIMPLE
	
	@GetMapping("/display")
	  public List<Company> getCompany() {
	    System.out.println("Les companys sont...");
	 
	    List<Company> Companys = new ArrayList<>();
	    companyRepository.findAll().forEach(Companys::add);
	 
	    return Companys;
	  }
	
	@GetMapping("/nb")
	public Long compter() {
		Long nb= companyRepository.count();
		return nb;	
	}
	// jdid
	@GetMapping("/{login}/{pwd}")
	public ResponseEntity<Company> getCompanyById(@PathVariable String login, String pwd)
			throws ResourceNotFoundException {
		Company Company = companyRepository.findByLogin(login)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this hello id :: " + login));
		return ResponseEntity.ok().body(Company);
	}
	
	@GetMapping("/5/{login}/{pwd}")
	  public List<Company> getAllusCompanys(@PathVariable String login, @PathVariable String pwd) {
	    System.out.println("Get all Companys with ..."+login);
	 
	    List<Company> Company = new ArrayList<>();
	    companyRepository.findByPwd(pwd).forEach(Company::add);
	 
	    return Company;
	  }
	
	
	@PostMapping("/add-company")
	@ResponseBody
	public void ajouterCompany(@RequestBody Company company) {
		System.out.println("Controller");
		companyService.ajouterCompany(company);
		
	}
	//zedtou jdid 
	@GetMapping("/company/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long CompanyId)
			throws ResourceNotFoundException {
		Company company = companyRepository.findById(CompanyId)
				.orElseThrow(() -> new ResourceNotFoundException("  not   :: " + CompanyId));
		return ResponseEntity.ok().body(company);
	}
	//zedtou jdid 
	@PostMapping("/add-company2")
	public ResponseEntity<Object> createCompany(@Valid @RequestBody Company company) {
		if(companyRepository.existsByLogin(company.getLogin()) || companyRepository.existsByEmail(company.getEmail()) ) {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			
		}
	      return new ResponseEntity<>(companyRepository.save(company), HttpStatus.OK);
	}
	
	@DeleteMapping("/companydelete/{id}")
	public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long CompanyId)
			throws ResourceNotFoundException {
		Company Company = companyRepository.findById(CompanyId)
				.orElseThrow(() -> new ResourceNotFoundException("Frais not found  id :: " + CompanyId));

		companyRepository.delete(Company);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/delete")
	  public ResponseEntity<String> deleteAllCompanys() {
	    System.out.println("Delete All Companys...");
	 
	    companyRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Companys have been deleted!", HttpStatus.OK);
	  }
	
	 @PutMapping("/modify/{id}")
	  public ResponseEntity<Company> updateCompany(@PathVariable("id") long id, @RequestBody Company Company) {
	    System.out.println("Update Company with ID = " + id + "...");
	 
	    Optional<Company> CompanyInfo = companyRepository.findById(id);
	 
	    if (CompanyInfo.isPresent()) {
	    	Company company = CompanyInfo.get();
	    	company.setName(Company.getName());
	    	company.setAddress(Company.getAddress());
	    	company.setEmail(Company.getEmail());
	    	company.setPhone(Company.getPhone());
	    	company.setImage(Company.getImage());
	    	company.setLogin(Company.getLogin());
	    	company.setPwd(Company.getPwd());
	    	company.setRole(Company.getRole());
	    	company.setInvitations(Company.getInvitations());
	    	company.setEmployees(Company.getEmployees());
	           
	      return new ResponseEntity<>(companyRepository.save(Company), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	 
	 // APRES CRUD ICI :)

}
