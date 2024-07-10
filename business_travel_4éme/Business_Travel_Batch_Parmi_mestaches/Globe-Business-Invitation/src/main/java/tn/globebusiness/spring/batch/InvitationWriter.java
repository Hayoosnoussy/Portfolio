package tn.globebusiness.spring.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tn.globebusiness.spring.Entities.Invitation;
import tn.globebusiness.spring.Services.InvitationService;

@Slf4j
public class InvitationWriter implements ItemWriter<Invitation> {

    @Autowired
    private InvitationService invitationService;

    /* écrire nos données dans la base de données*/
    public void write(List<? extends Invitation> invitations) {
    	invitationService.addinvitations((List<Invitation>) invitations);
     //    log.info("Enregistrement des lignes stocks dans la base de données", invitations);
            
    }
}
