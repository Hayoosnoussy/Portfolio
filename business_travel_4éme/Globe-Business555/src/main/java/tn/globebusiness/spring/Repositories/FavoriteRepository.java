package tn.globebusiness.spring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Favorite;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
	
	@Query("select f from Favorite f where f.employee.id = 1")
	List<Favorite> getFavoriteByEmployee();

}
