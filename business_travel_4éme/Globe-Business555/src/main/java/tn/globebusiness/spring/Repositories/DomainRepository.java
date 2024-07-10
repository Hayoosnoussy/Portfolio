package tn.globebusiness.spring.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.globebusiness.spring.Entities.Domain;

@Repository
public interface DomainRepository extends CrudRepository<Domain, Long> {

	Domain findDomainByName(String name);

	@Transactional
	@Modifying
	@Query("delete from Domain d where d.name = ?1")
	void deleteDomainByName(String name);

}