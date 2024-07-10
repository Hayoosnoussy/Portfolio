package tn.globebusiness.spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.globebusiness.spring.Entities.Domain;
import tn.globebusiness.spring.Services.IDomainService;

@RestController
public class DomainRestController {
	@Autowired
	IDomainService ids;

	@PostMapping("domain/add")
	public Domain addDomain(@RequestBody Domain domain) {
		return ids.addDomain(domain);
	}

	@PutMapping("domain/{name}/edit")
	public Domain updateDomain(@PathVariable("name") String name, Domain domain) {
		return ids.updateDomain(name, domain);
	}

	@DeleteMapping("domain/{name}/delete")
	void deleteDomain(@PathVariable("name") String name) {
		ids.deleteDomain(name);
	}
}
