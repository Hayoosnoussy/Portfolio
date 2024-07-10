package tn.globebusiness.spring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Employee;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	Employee findByName(String name);

	public Page<Employee> findByName(String n, Pageable pageable);

	public boolean existsByEmail(String email);

	public boolean existsByLogin(String login);

	public Optional<Employee> findByLogin(String login);

	public Iterable<Employee> findByPwd(String pwd);

}