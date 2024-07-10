package tn.globebusiness.spring.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.globebusiness.spring.Entities.Invitation;
import tn.globebusiness.spring.Repositories.CompanyRepository;
import tn.globebusiness.spring.Repositories.InvitationRepository;

@Service
public class InvitationService implements IInvitation {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	InvitationRepository invitationRepository;

	@Override
	public void ajouterInvitation(Invitation invitation) {
		invitationRepository.save(invitation);

	}

	@Override
	public List<Invitation> addinvitations(List<Invitation> list) {
		return invitationRepository.saveAll(list);
	}

	@Override
	public List<Invitation> getInvitationByCompany(long id) {
		// TODO Auto-generated method stub
		return invitationRepository.getInvitationByCompany(id);
	}

	@Override
	public List<Invitation> getMailByStatus() {
		// TODO Auto-generated method stub
		return invitationRepository.getMailByStatus();
	}

	@Override
	public List<Invitation> getInvitationWaiting() {
		// TODO Auto-generated method stub
		return invitationRepository.getInvitationWaiting();

	}

	@Override
	public List<Invitation> getInvitationDone() {
		// TODO Auto-generated method stub
		return invitationRepository.getInvitationDone();
	}

	@Override
	public List<Invitation> retrieveInvitationsDate(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return invitationRepository.retrieveInvitationsDate(date1, date2);

	}

}
