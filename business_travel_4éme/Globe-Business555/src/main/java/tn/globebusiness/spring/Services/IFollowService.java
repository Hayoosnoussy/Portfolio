package tn.globebusiness.spring.Services;

import java.util.List;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Follow;

public interface IFollowService {
	Follow addFollow(Long idEmployee1, Long idEmployee2);

	// Follow deleteFollow(Long idEmployee);
	void acceptFollow(Long idFollow);

	List<Employee> myFrinds(Long idEmployee1, int etat);

	List<Employee> myInvitations(Long idEmployee2, int etat);
}
