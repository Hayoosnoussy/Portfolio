package tn.globebusiness.spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Exception.ResourceNotFoundException;
import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Post;
import tn.globebusiness.spring.Repositories.CompanyRepository;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.InvitationRepository;
import tn.globebusiness.spring.Services.CompanyServiceImpl;
import tn.globebusiness.spring.Services.EmployeeServiceImpl;
import tn.globebusiness.spring.Services.IEmployeeService;
import tn.globebusiness.spring.Services.InvitationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class EmployeeRestController {
	@Autowired
	IEmployeeService ies;
	@Autowired
	EmployeeRepository er;

	@PutMapping("/employee/{name}/edit")
	public Employee edit(@PathVariable("name") String name, @RequestBody Employee newEmployee) throws Exception {
		return ies.UpdateEmployee(name, newEmployee);
	}

	@GetMapping("/employee/{name}")
	public Employee display(@PathVariable("name") String name) throws Exception {
		return ies.DisplayEmployeeData(name);
	}

	@GetMapping("/employee/{name}/posts")
	public List<Post> DisplayPosts(@PathVariable("name") String name, Long employeeId) throws Exception {
		return ies.DisplayPostHistory(name, employeeId);
	}

	/*@Autowired
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

	@GetMapping("/displayAllEmployees")
	public List<Employee> getEmployee() {
		System.out.println("Les Employees sont...");

		List<Employee> Employees = new ArrayList<>();
		employeeRepository.findAll().forEach(Employees::add);

		return Employees;
	}

	@GetMapping("/nbOfEmployees")
	public Long compter() {
		Long nb = employeeRepository.count();
		return nb;
	}

	@PostMapping("/add-employee")
	@ResponseBody
	public void ajouterEmployee(@RequestBody Employee employee) {
		System.out.println("Controller");
		employeeService.ajouterEmployee(employee);
	}

	// jdid
	@GetMapping("/{login}/{pwd}")
	public ResponseEntity<Employee> getCompanyById(@PathVariable String login, String pwd)
			throws ResourceNotFoundException {
		Employee Employee = employeeRepository.findByLogin(login)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this hello id :: " + login));
		return ResponseEntity.ok().body(Employee);
	}

	@GetMapping("/5/{login}/{pwd}")
	public List<Employee> getAllusCompanys(@PathVariable String login, @PathVariable String pwd) {
		System.out.println("Get all Employees with ..." + login);

		List<Employee> Employee = new ArrayList<>();
		employeeRepository.findByPwd(pwd).forEach(Employee::add);

		return Employee;
	}

	// zedtou jdid
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long EmployeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(EmployeeId)
				.orElseThrow(() -> new ResourceNotFoundException("  not   :: " + EmployeeId));
		return ResponseEntity.ok().body(employee);
	}

	// zedtou jdid
	@PostMapping("/add-employee2")
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) {
		if (employeeRepository.existsByLogin(employee.getLogin())
				|| employeeRepository.existsByEmail(employee.getEmail())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
	}

	@DeleteMapping("/employeedelete/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long EmployeeId)
			throws ResourceNotFoundException {
		Employee Employee = employeeRepository.findById(EmployeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Frais not found  id :: " + EmployeeId));

		employeeRepository.delete(Employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/deleteAllEmployees")
	public ResponseEntity<String> deleteAllEmployees() {
		System.out.println("Delete All Employees...");

		employeeRepository.deleteAll();

		return new ResponseEntity<>("All Employees have been deleted!", HttpStatus.OK);
	}

	@PutMapping("/modifyEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee Employee) {
		System.out.println("Update Employee with ID = " + id + "...");

		Optional<Employee> EmployeeInfo = employeeRepository.findById(id);

		if (EmployeeInfo.isPresent()) {
			Employee employee = EmployeeInfo.get();
			employee.setName(Employee.getName());
			employee.setLastName(Employee.getLastName());
			employee.setEmail(Employee.getEmail());
			employee.setPhone(Employee.getPhone());
			employee.setImage(Employee.getImage());
			employee.setLogin(Employee.getLogin());
			employee.setPwd(Employee.getPwd());
			employee.setBirthday(Employee.getBirthday());
			employee.setRole(Employee.getRole());
			employee.setCompany(Employee.getCompany());

			return new ResponseEntity<>(employeeRepository.save(Employee), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
}
