package tn.globebusiness.spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.globebusiness.spring.Entities.Profession;
import tn.globebusiness.spring.Services.IProfessionService;

@RestController
public class ProfessionRestController {
	@Autowired
	IProfessionService ips;

	@PostMapping("/professions/add")
	public Profession addProfession(@RequestBody Profession profession) {
		return ips.addProfession(profession);
	}

	@PutMapping("/professions/{name}/edit")
	public Profession updateProfession(@PathVariable("name") String name, @RequestBody Profession profession)
			throws Exception {
		return ips.updateProfession(name, profession);
	}

	@DeleteMapping("/professions/{name}/delete")
	public void deleteProfession(@PathVariable("name") String name) throws Exception {
		ips.deleteProfession(name);
	}

}
