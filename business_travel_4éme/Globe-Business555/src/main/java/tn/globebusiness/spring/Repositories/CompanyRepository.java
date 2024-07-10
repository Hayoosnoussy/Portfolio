package tn.globebusiness.spring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Company;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
	Company findByName(String name);

	public Page<Company> findByName(String n, Pageable pageable);

	public boolean existsByEmail(String email);

	public boolean existsByLogin(String login);

	public Optional<Company> findByLogin(String login);

	public Iterable<Company> findByPwd(String pwd);

}