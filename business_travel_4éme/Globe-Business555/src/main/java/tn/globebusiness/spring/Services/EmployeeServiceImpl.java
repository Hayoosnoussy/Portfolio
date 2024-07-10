package tn.globebusiness.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Post;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.PostRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	EmployeeRepository er;
	@Autowired
	PostRepository pr;

	@Override
	public Employee UpdateEmployee(String employeeName, Employee newEmployee) throws Exception {
		Employee employee = er.findByName(employeeName);
		if (employee != null) {
			employee.setName(newEmployee.getName());
			employee.setLastName(newEmployee.getLastName());
			employee.setBirthday(newEmployee.getBirthday());
			employee.setEmail(newEmployee.getEmail());
			employee.setGender(newEmployee.getGender());
			employee.setPhone(newEmployee.getPhone());
		} else {
			throw new Exception("Employee not found");
		}
		return er.save(employee);
	}

	@Override
	public Employee DisplayEmployeeData(String name) throws Exception {
		if (er.findByName(name) != null) {
			return er.findByName(name);
		} else {
			throw new Exception("Employee not found");
		}
	}

	@Override
	public List<Post> DisplayPostHistory(String name, Long id) throws Exception {
		Employee employee = er.findByName(name);
		List<Post> posts = pr.HistoryPosts(employee.getId());
		if (posts.isEmpty()) {
			throw new Exception("No posts yet for this employee");
		} else {
			return posts;
		}
	}

	@Override
	public void ajouterEmployee(Employee employee) {
		er.save(employee);
	}
}
