package tn.globebusiness.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.Chat;
import tn.globebusiness.spring.Services.IChatService;

@RestController
public class ChatRestController {
	@Autowired
	IChatService ics;

	@PostMapping("addChat")
	public Chat addChat(@RequestParam("idEmployee") Long idEmployee, @RequestParam("idTravel") Long idTravel,
			@RequestBody Chat chat) {
		return ics.addChat(idEmployee, idTravel, chat);
	}

	@GetMapping("getDiscussion")
	public List<String> getDiscussion(@RequestParam("idTravel") Long idTravel) {
		return ics.getDiscussion(idTravel);
	}
}
