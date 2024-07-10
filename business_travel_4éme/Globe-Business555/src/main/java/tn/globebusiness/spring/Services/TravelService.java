package tn.globebusiness.spring.Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Travel;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.TravelRepository;
import tn.globebusiness.spring.Services.ITravelService;

@Service
public class TravelService implements ITravelService {
	@Autowired
	TravelRepository travelrep;
	@Autowired
	EmployeeRepository emprep;

	@Override
	public void addTravel(Travel travel) {
		travelrep.save(travel);

	}

	@Override
	public List<Travel> retrieveAllTravel() {
		return (List<Travel>) travelrep.findAll();
	}

	@Override
	public void DeleteTravel(long id) {
		travelrep.deleteById(id);

	}

	@Override
	public void UpdateTravel(Travel travel, long id) {
		Travel t = travelrep.getById(id);
		t.setDate_begin(travel.getDate_begin());
		t.setDestination(travel.getDestination());
		t.setDate_end(travel.getDate_end());
		t.setObjective(travel.getObjective());
		t.setState(travel.getState());
		t.setCity(travel.getCity());
		travelrep.save(t);

	}

	@Override
	public Optional<Travel> retrieveTravelById(long id) {
		return travelrep.findById(id);
	}

	// every day at 10am this function triggers to delete expired travel
	@Scheduled(cron = "0 0 10 * * *", zone = "Africa/Tunis")
	@Override
	public void Autodeletetravel() {
		List<Travel> travels = travelrep.findAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		for (Travel t : travels) {
			LocalDate date = LocalDate.parse((CharSequence) t.getDate_end(), formatter);
			LocalDate today = LocalDate.now();
			if (today.isAfter(date)) {
				travelrep.deleteById(t.getId());

			}
		}

	}

	@Override
	public List<Travel> findBydestination(String destination) {
		return (List<Travel>) travelrep.findBydestination(destination);
	}

	@Override
	public List<Travel> findBystate(String state) {
		return (List<Travel>) travelrep.findBystate(state);
	}

	@Override
	public List<Travel> findBycity(String city) {
		return (List<Travel>) travelrep.findBycity(city);
	}

	@Override
	public List<Travel> findBydestinationAndState(String destination, String state) {
		return (List<Travel>) travelrep.findBydestinationAndState(destination, state);
	}

	@Override
	public List<Travel> findBydestinationAndStateAndCity(String destination, String state, String city) {
		return (List<Travel>) travelrep.findBydestinationAndStateAndCity(destination, state, city);
	}

	@Override
	public void Affecttraveler(long id, long id2) {
		Travel t = travelrep.getById(id);
		Employee e = emprep.findById(id2).orElse(new Employee());
		t.setEmployee(e);
		e.setTravel(t);
		travelrep.save(t);
		emprep.save(e);

	}

	// add and affect at same time
	@Override
	public void AddandAffectTravel(Travel travel, long id) {
		Travel t = new Travel();
		t.setDate_begin(travel.getDate_begin());
		t.setDestination(travel.getDestination());
		t.setDate_end(travel.getDate_end());
		t.setState(travel.getState());
		t.setCity(travel.getCity());
		t.setObjective(travel.getObjective());
		Employee e = emprep.findById(id).orElse(new Employee());
		t.setEmployee(e);
		e.setTravel(t);
		emprep.save(e);
		travelrep.save(t);
	}

	// compared to today's date
	@Override
	public List<Travel> findAllTravelbydate() {
		return (List<Travel>) travelrep.findAllTravelbydate();
	}

	// MATCHIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIG!!!!!!!!!!!
	@Override
	public List<Employee> Matching(long id) {
		List<Employee> matchedemp = new ArrayList<Employee>();
		Employee user = emprep.findById(id).orElse(new Employee());
		Travel travel = travelrep.getById(user.getTravel().getId());
		String destinationO = travel.getDestination();
		String stateO = travel.getState();
		Date date1 = travel.getDate_begin();
		Date date2 = travel.getDate_end();
		List<Travel> travels = travelrep.retrievTravelbyspecificDate(date1, date2);
		for (Travel t1 : travels) {
			String destination1 = t1.getDestination();
			String state1 = t1.getState();
			if (!user.getId().equals(t1.getEmployee().getId())) {
				if (destinationO.equals(destination1)) {
					if (stateO.equals(state1)) {
						if (travel.getCompany().getDomain().getName().equals(t1.getCompany().getDomain().getName())) {
							Employee emp1 = t1.getEmployee();
							matchedemp.add(emp1);
						}
					}
				}

			}

		}

		return matchedemp;
	}

	// compared to very specific date
	@Override
	public List<Travel> retrievTravelbyspecificDate(Date date1, Date date2) {
		return (List<Travel>) travelrep.retrievTravelbyspecificDate(date1, date2);
	}

}
