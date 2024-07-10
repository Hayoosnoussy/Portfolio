package tn.globebusiness.spring.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import tn.globebusiness.spring.Entities.Category;
import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends CrudRepository<Company, Long>, JpaRepository<Company, Long> {
    Company findByName1(String name); 
	public List<Company> findByName(String n);
	public Page<Company> findByName(String n,Pageable pageable);
	public boolean existsByEmail(String email);
	public boolean existsByLogin(String login);
	public Optional<Company> findByLogin(String login);
	public Iterable<Company> findByPwd(String pwd);
}