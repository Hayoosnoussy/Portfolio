package tn.globebusiness.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Repositories.CompanyRepository;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.InvitationRepository;

@Service
@Slf4j
public class EmployeeService implements IEmployee{
	
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	InvitationRepository invitationRepository; 
	
	@Override
	public void ajouterEmployee(Employee employee) {
		employeeRepository.save(employee);
		}
}
