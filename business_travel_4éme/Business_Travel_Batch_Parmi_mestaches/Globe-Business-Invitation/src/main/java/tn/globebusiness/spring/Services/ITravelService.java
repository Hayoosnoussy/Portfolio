package tn.globebusiness.spring.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Travel;

public interface ITravelService {
	public void addTravel(Travel travel);
	List<Travel> retrieveAllTravel();
	void DeleteTravel(long id);
	void UpdateTravel (Travel travel,long id);
	Optional<Travel> retrieveTravelById(long id);
	void Autodeletetravel ();
	List<Travel> findBydestination(String destination);
	List<Travel> findAllTravelbydate();
	List<Travel> findBystate(String state);
	List<Travel> findBycity(String city);
	List<Travel> findBydestinationAndState(String destination,String state);
	List<Travel> findBydestinationAndStateAndCity(String destination,String state,String city);
	void Affecttraveler(long id,long id2);
	void AddandAffectTravel(Travel travel,long id);
	List<Employee> Matching(long id);
	List<Travel> retrievTravelbyspecificDate(@Param("date1") Date date1, @Param("date2") Date date2);
	

}
