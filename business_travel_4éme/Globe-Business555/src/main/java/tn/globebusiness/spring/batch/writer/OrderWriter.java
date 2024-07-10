package tn.globebusiness.spring.batch.writer;

import lombok.extern.slf4j.Slf4j;
import tn.globebusiness.spring.Entities.Invitation;
import tn.globebusiness.spring.Repositories.InvitationRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class OrderWriter implements ItemWriter<Invitation> {
	@Autowired
	InvitationRepository invitationRepository;

	@Override
	public void write(List<? extends Invitation> list) throws Exception {
		log.debug("item writer: {}", list.get(0));
		invitationRepository.saveAllAndFlush(list);
	}
}
