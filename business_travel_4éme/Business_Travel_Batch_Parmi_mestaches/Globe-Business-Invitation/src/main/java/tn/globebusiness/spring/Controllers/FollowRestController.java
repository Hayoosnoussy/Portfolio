package tn.globebusiness.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Follow;
import tn.globebusiness.spring.Services.IFollowService;

@RestController
public class FollowRestController {
	@Autowired
	IFollowService ifs;
	
	@PostMapping("addFollow")
	public Follow addFollow(@RequestParam("idEmployee1")Long idEmployee1,@RequestParam("idEmployee2") Long idEmployee2){
		return ifs.addFollow(idEmployee1, idEmployee2);
		
	}
	
	@PutMapping("acceptFollow")
	public void acceptFollow(@RequestParam("idFollow")Long idFollow){
	      ifs.acceptFollow(idFollow);
		
	}
	
	@GetMapping("myFrinds")
	public List<Employee> myFrineds(@RequestParam("idEmployee1") Long idEmployee1){
		return ifs.myFrinds(idEmployee1, 1);
	}
	
	@GetMapping("myInvitations")
	public List<Employee> myInvitations(@RequestParam("idEmployee2") Long idEmployee2){
		return ifs.myInvitations(idEmployee2, 0);
	}
}
