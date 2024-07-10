package tn.globebusiness.spring.batch.processor;

import lombok.extern.slf4j.Slf4j;
import tn.globebusiness.spring.Entities.Invitation;
import tn.globebusiness.spring.orders.email.EmailServiceImpl;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.SendFailedException;

@Slf4j
public class OrderItemProcessor implements ItemProcessor<Invitation, Invitation> {

    @Autowired
    EmailServiceImpl emailService;

    @Override
    public Invitation process(Invitation Invitation) throws Exception {
        log.debug("processor: {}", Invitation);
        try {
            emailService.sendSimpleMessage(Invitation.getDestinataireEmail(), "Invitation  ", "Bonjour "
            		+ "Rejoindre notre plateform pour rendre vos voyage de travail plus facile ");
            Invitation.setSentStatus(true);
        } catch (SendFailedException sendFailedException) {
            log.debug("error: {}", sendFailedException.getMessage());
        }
        return Invitation;
    }
}
