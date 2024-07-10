package tn.globebusiness.spring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Follow;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.FollowRepository;

@Service
public class FollowService implements IFollowService {
	@Autowired
	EmployeeRepository er;
	@Autowired
	FollowRepository fr;

	@Override
	public Follow addFollow(Long idEmployee1, Long idEmployee2) {
		Employee employee1 = er.findById(idEmployee1).orElse(new Employee());
		Employee employee2 = er.findById(idEmployee2).orElse(new Employee());
		Follow follow = fr.findByIdEmp1Emp2(employee1, employee2).orElse(new Follow());

		if (follow.getIdFollow() == null) {
			follow = new Follow();
			follow.setEmployee1(employee1);
			follow.setEmployee2(employee2);
			follow.setDateFollow(new Date());
			follow.setEtat(0);
			return fr.save(follow);
		} else {
			fr.delete(follow);
		}
		return follow;
	}

	/*
	 * @Override public Follow deleteFollow(Long idEmployee) { // TODO
	 * Auto-generated method stub return null; }
	 */

	@Override
	public void acceptFollow(Long idFollow) {
		Follow follow = fr.findById(idFollow).get();
		follow.setEtat(1);
		fr.save(follow);
	}

	@Override
	public List<Employee> myFrinds(Long idEmployee1, int etat) {
		
		List<Follow> follows = fr.myFrinds(er.findById(idEmployee1).get(), etat);
		
		List<Employee> frinds = new ArrayList<Employee>();
		for (Follow f : follows) {
			if (f.getEmployee1().getId() == idEmployee1)
				frinds.add(f.getEmployee2());
			else
				frinds.add(f.getEmployee1());
		}
		return frinds;
	}

	@Override
	public List<Employee> myInvitations(Long idEmployee2, int etat) {
		List<Follow> follows = fr.myInvitations(er.findById(idEmployee2).get(), etat);
		List<Employee> frinds = new ArrayList<Employee>();
		for (Follow f : follows) {
			frinds.add(f.getEmployee1());
		}
		return frinds;
	}

}
