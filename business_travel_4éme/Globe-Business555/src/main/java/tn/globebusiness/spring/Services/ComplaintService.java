package tn.globebusiness.spring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Complaint;
import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.NotifComplaint;
import tn.globebusiness.spring.Entities.Travel;
import tn.globebusiness.spring.Repositories.ComplaintRepository;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.NotifComplaintRepository;
import tn.globebusiness.spring.Repositories.TravelRepository;

@Service
public class ComplaintService implements IComplaintService {

	@Autowired
	ComplaintRepository cr;
	@Autowired
	EmployeeRepository er;
	@Autowired
	TravelRepository tr;
	@Autowired
	NotifComplaintRepository nfr;

	@Override
	public Complaint addComplaint(Complaint complaint, Long idEmployee, Long idTravel) {
		complaint.setEmployee(er.findById(idEmployee).get());
		complaint.setTravel(tr.findById(idTravel).get());
		complaint.setFeedbackDate(new Date());
		complaint.setEtat(0);
		return cr.save(complaint);
	}

	@Override
	public void deleteComplaint(Long idC) {
		cr.deleteById(idC);
	}

	@Override
	public Optional<Complaint> displayComplaint(Long idC) {
		return cr.findById(idC);
	}

	@Override
	public List<Complaint> displayAllUserComplaints(Long idEmployee) {
		Employee employee = er.findById(idEmployee).get();
		return (List<Complaint>) cr.displayAllUserComplaints(employee);
	}

	public List<Complaint> findByTitle(String Title) {
		return (List<Complaint>) cr.findByTitle(Title);
	}

	@Override
	public List<Complaint> displayAllTravelComplaints(Long idTravel) {
		Travel travel = tr.findById(idTravel).get();
		List<Complaint> complaints = (List<Complaint>) cr.displayAllTravelComplaints(travel);
		if (!complaints.isEmpty()) {
			complaints.sort((p1, p2) -> -(p1.getFeedbackDate().compareTo(p2.getFeedbackDate())));
		}
		return complaints;
	}

	@Override
	public String traiterReclamation(Long idReclamation, NotifComplaint nf) {
		Complaint complaint = cr.findById(idReclamation).get();
		if (complaint.getEtat() == 0) {
			complaint.setEtat(1);
			cr.save(complaint);
			nf.setComplaint(complaint);
			nf.setDateNotif(new Date());
			nfr.save(nf);
			return "La Reclamation de " + complaint.getEmployee().getName() + " sur le voyage "
					+ complaint.getTravel().getDestination() + " a été traite ";
		}

		return "reclamation deja traite ";
	}

	@Override
	public List<NotifComplaint> afficherNotifPourEmployee(Long idEmployee) {
		List<Complaint> complaints = cr.displayAllUserComplaints(er.findById(idEmployee).get());
		List<NotifComplaint> notifs = new ArrayList<NotifComplaint>();
		for (Complaint c : complaints) {
			if (c.getEtat() == 1)
				notifs.add(nfr.findById(c.getIdC()).get());
		}
		return notifs;
	}

}
