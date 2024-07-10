package tn.globebusiness.spring.batch;

import org.springframework.batch.item.ItemProcessor;

import tn.globebusiness.spring.Entities.Invitation;

public class InvitationProcessor implements ItemProcessor<Invitation, Invitation> {
	/*11. logique métier de notre job*/
	@Override
	public Invitation process(Invitation invitation) {
		
		invitation.setId(invitation.getId());
		invitation.setDate(invitation.getDate());
		invitation.setCompany(invitation.getCompany());

		invitation.setDestinataireEmail(invitation.getDestinataireEmail());
		invitation.setSentStatus(invitation.isSentStatus());
		return invitation;
	}
}

