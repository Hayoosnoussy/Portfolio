package tn.globebusiness.spring.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Follow;
@Repository
public interface FollowRepository extends CrudRepository<Follow,Long> {
	@Query("select f from Follow f where f.employee1= :employee1 and f.employee2= :employee2")
	Optional<Follow> findByIdEmp1Emp2(@Param("employee1") Employee employee1,@Param("employee2") Employee employee2);
	
	@Query("select f from Follow f where  (f.employee1=:employee1 or f.employee2= :employee1)  and f.etat=:etat")
	List<Follow> myFrinds(@Param("employee1") Employee employee1,@Param("etat") int etat);
	
	@Query("select f from Follow f where f.employee2=:employee2 and f.etat=:etat")
	List<Follow> myInvitations(@Param("employee2") Employee employee2,@Param("etat") int etat);
}
