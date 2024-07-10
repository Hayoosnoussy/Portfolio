package tn.globebusiness.spring.Services;

import java.util.List;

import tn.globebusiness.spring.Entities.Chat;

public interface IChatService {
	Chat addChat(Long idEmployee, Long idTravel, Chat chat);

	List<String> getDiscussion(Long idTravel);
}
