package tn.globebusiness.spring.Controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Travel;
import tn.globebusiness.spring.Services.ITravelService;

@RestController
@RequestMapping("/travelcontrol")
public class TravelController {
	@Autowired
	ITravelService itravelserv;

	@PostMapping("/addtravel")
	@ResponseBody
	public void addTravel(@RequestBody Travel travel) {
		itravelserv.addTravel(travel);

	}

	@GetMapping("/listtravel")
	@ResponseBody
	public List<Travel> retrieveAllTravel() {
		return itravelserv.retrieveAllTravel();

	}

	@PostMapping("/deletetravel/{id}")
	public void DeleteTravel(@PathVariable Long id) {
		itravelserv.DeleteTravel(id);

	}

	@PostMapping("/updatetravel/{id}")
	@ResponseBody
	public void UpdateTravel(@RequestBody Travel travel, @PathVariable long id) {
		itravelserv.UpdateTravel(travel, id);
	}

	@GetMapping("/retrieveby/{id}")
	@ResponseBody
	public Optional<Travel> retrieveTravelById(@PathVariable long id) {
		return itravelserv.retrieveTravelById(id);

	}

	@PostMapping("/updatetraveler/{id}/{id2}")
	@ResponseBody
	public void Affecttraveler(@PathVariable long id, @PathVariable long id2) {
		itravelserv.Affecttraveler(id, id2);
	}

	@PostMapping("/addtravelandaffect/{id}")
	@ResponseBody
	public void AddandAffectTravel(@RequestBody Travel travel, @PathVariable long id) {
		itravelserv.AddandAffectTravel(travel, id);
	}

	@GetMapping("/findbydestination/{destination}")
	@ResponseBody
	public List<Travel> findBydestination(@PathVariable String destination) {
		return itravelserv.findBydestination(destination);

	}

	@GetMapping("/findbystate/{state}")
	@ResponseBody
	public List<Travel> findBystate(@PathVariable String state) {
		return itravelserv.findBystate(state);

	}

	@GetMapping("/findbycity/{city}")
	@ResponseBody
	public List<Travel> findBycity(@PathVariable String city) {
		return itravelserv.findBycity(city);

	}

	@GetMapping("/findbydeststate/{destination}/{state}")
	@ResponseBody
	public List<Travel> findBydestinationAndState(@PathVariable String destination, @PathVariable String state) {
		return itravelserv.findBydestinationAndState(destination, state);

	}

	@GetMapping("/findbydeststatecity/{destination}/{state}/{city}")
	@ResponseBody
	public List<Travel> findBydestinationAndStateAndcity(@PathVariable String destination, @PathVariable String state,
			@PathVariable String city) {
		return itravelserv.findBydestinationAndStateAndCity(destination, state, city);

	}

	@GetMapping("/findTravelbyToday")
	@ResponseBody
	public List<Travel> findAllTravelbydate() {
		return itravelserv.findAllTravelbydate();

	}

	@GetMapping("/findTravelbyspecificdate/{date1}/{date2}")
	@ResponseBody
	public List<Travel> retrievTravelbyspecificDate(@PathVariable("date1") Date date1,
			@PathVariable("date2") Date date2) {
		return itravelserv.retrievTravelbyspecificDate(date1, date2);
	}

	@GetMapping("/travelmatching/{id}")
	@ResponseBody
	public List<Employee> Matching(@PathVariable long id) {
		return itravelserv.Matching(id);

	}

}
