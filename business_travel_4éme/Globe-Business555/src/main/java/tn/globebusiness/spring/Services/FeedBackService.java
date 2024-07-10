package tn.globebusiness.spring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.FeedBack;
import tn.globebusiness.spring.Entities.Travel;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.FeedBackRepository;
import tn.globebusiness.spring.Repositories.TravelRepository;

@Service
public class FeedBackService implements IFeedBackService {
	@Autowired
	FeedBackRepository fr;
	@Autowired
	EmployeeRepository er;
	@Autowired
	TravelRepository tr;

	@Override
	public FeedBack addFeedback(FeedBack f, Long idEmployee, Long idTravel) {
		f.setEmployee(er.findById(idEmployee).get());
		f.setTravel(tr.findById(idTravel).get());
		f.setFeedbackDateAndTime(new Date());
		return fr.save(f);
	}

	@Override
	public void deleteFeedback(Long idF) {
		fr.deleteById(idF);
	}

	@Override
	public FeedBack updateFeedback(Long idFeedBack, Long idEmployee, FeedBack f) {
		FeedBack feedBackTest = fr.findById(idFeedBack).orElse(new FeedBack());
		Employee employee = er.findById(idEmployee).orElse(new Employee());
		if (feedBackTest.getEmployee() == employee) {
			f.setFeedbackDateAndTime(feedBackTest.getFeedbackDateAndTime());
			f.setEmployee(feedBackTest.getEmployee());
			f.setTravel(feedBackTest.getTravel());
		}
		return fr.save(f);
	}

	@Override
	public FeedBack displayFeedback(Long idF) {
		return fr.findById(idF).get();
	}

	@Override
	public List<FeedBack> displayAllUserFeedBack(Long idEmployee) {
		Employee employee = er.findById(idEmployee).get();
		return (List<FeedBack>) fr.displayAllUserFeedBack(employee);
	}

	@Override
	public List<FeedBack> displayAllTravelFeedBack(Long idTravel) {
		Travel travel = tr.findById(idTravel).get();
		List<FeedBack> feedBacks = (List<FeedBack>) fr.displayAllTravelFeedBack(travel);
		if (!feedBacks.isEmpty()) {
			feedBacks.sort((p1, p2) -> (p1.getFeedbackDateAndTime().compareTo(p2.getFeedbackDateAndTime())));
		}
		return feedBacks;
	}

	@Override
	public List<Travel> trierTravelByFeedBacks() {
		List<Travel> travels = new ArrayList<Travel>();
		travels = (List<Travel>) tr.findAll();
		if (!travels.isEmpty()) {
			travels.sort((t1, t2) -> -(t1.getFeedBacks().size() - t2.getFeedBacks().size()));
		}

		return travels;
	}

}
