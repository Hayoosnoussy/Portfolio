package tn.globebusiness.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.FeedBack;
import tn.globebusiness.spring.Entities.Travel;
import tn.globebusiness.spring.Services.IFeedBackService;

@RestController
public class FeedBackRestController {
	@Autowired
	IFeedBackService ifs;

	@PostMapping("addFeedBack")
	public FeedBack addFeedback(@RequestBody FeedBack f, @RequestParam("idEmployee") Long idEmployee,
			@RequestParam("idTravel") Long idTravel) {
		return ifs.addFeedback(f, idEmployee, idTravel);
	}

	@GetMapping("trierTravelByFeedBacks")
	public List<Travel> trierTravelByFeedBacks() {
		return ifs.trierTravelByFeedBacks();
	}

	@DeleteMapping("deleteFeedBack")
	public void deleteFeedback(@RequestParam("idF") Long idF) {
		ifs.deleteFeedback(idF);
	}

	@PutMapping("updateFeedBack")
	public FeedBack updateFeedback(@RequestParam("idFeedBack") Long idFeedBack,
			@RequestParam("idEmployee") Long idEmployee, @RequestBody FeedBack f) {
		return ifs.updateFeedback(idFeedBack, idEmployee, f);
	}

	@GetMapping("displayAllTravelFeedBack")
	public List<FeedBack> displayAllTravelFeedBack(@RequestParam("idTravel") Long idTravel) {
		return ifs.displayAllTravelFeedBack(idTravel);
	}

}
