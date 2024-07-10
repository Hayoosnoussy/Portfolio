package tn.globebusiness.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Repositories.CompanyRepository;

@Service
@Slf4j

public class CompanyService implements ICompany {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public void ajouterCompany(Company company) {
		companyRepository.save(company);
		
		
	}

}
