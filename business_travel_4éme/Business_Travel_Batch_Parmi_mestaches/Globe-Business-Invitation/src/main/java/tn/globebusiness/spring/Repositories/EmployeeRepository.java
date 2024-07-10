package tn.globebusiness.spring.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long>,JpaRepository <Employee, Long> {
    Employee findByName1(String name);
	public List<Employee> findByName(String n);
	public Page<Employee> findByName(String n,Pageable pageable);
	public boolean existsByEmail(String email);
	public boolean existsByLogin(String login);
	public Optional<Employee> findByLogin(String login);
	public Iterable<Employee> findByPwd(String pwd);
}