package tn.globebusiness.spring.Repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Profession;

import java.util.List;

public interface ProfessionRepository extends CrudRepository<Profession, Long> {
    Profession findProfessionByName(String name);

    @Transactional
    @Modifying
    @Query("delete from Profession p where p.name = ?1")
    void deleteProfessionByName(String name);

   /* @Query("select p from Profession p inner join p.employees employees where employees.profession = ?1")
    List<Profession> findEmployees(Long professionId);*/







}