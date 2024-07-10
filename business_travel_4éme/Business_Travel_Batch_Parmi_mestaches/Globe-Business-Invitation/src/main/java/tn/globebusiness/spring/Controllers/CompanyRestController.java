package tn.globebusiness.spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Services.ICompanyService;

@RestController
public class CompanyRestController {
    @Autowired
    ICompanyService ics;
    @GetMapping("/company/{companyName}")
    public Company displayCompanyData(@PathVariable("companyName") String companyName) throws Exception {
        return ics.DisplayCompanyData(companyName);
    }
    @PutMapping("/company/{companyName}/edit")
    public Company updateCompany(@PathVariable("companyName") String companyName, @RequestBody Company company) throws Exception{
        return ics.UpdateCompany(companyName, company);
    }
}
