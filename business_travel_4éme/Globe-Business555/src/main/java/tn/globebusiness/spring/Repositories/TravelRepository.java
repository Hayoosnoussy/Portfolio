package tn.globebusiness.spring.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

	List<Travel> findBydestination(String destination);

	List<Travel> findBystate(String state);

	List<Travel> findBycity(String city);

	List<Travel> findBydestinationAndState(String destination, String state);

	List<Travel> findBydestinationAndStateAndCity(String destination, String state, String city);

	@Query("SELECT t FROM Travel t where CURRENT_DATE BETWEEN t.date_begin AND t.date_end")
	List<Travel> findAllTravelbydate();

	@Query("SELECT t FROM Travel t where t.date_begin>= :date1 AND t.date_begin<= :date2 OR :date1 >= t.date_begin AND :date1 <= t.date_end")
	List<Travel> retrievTravelbyspecificDate(@Param("date1") Date date1, @Param("date2") Date date2);
}
