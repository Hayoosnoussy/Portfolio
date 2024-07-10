package tn.globebusiness.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements ICompanyService {
	@Autowired
	CompanyRepository cr;

	@Override
	public Company UpdateCompany(String companyName, Company company) throws Exception {
		Company c = cr.findByName(companyName);
		if (c != null) {
			c.setName(company.getName());
			c.setEmail(company.getEmail());
			c.setAddress(company.getAddress());
			c.setPhone(company.getPhone());
		} else {
			throw new Exception("Company not found");
		}
		return cr.save(c);
	}

	@Override
	public Company DisplayCompanyData(String name) throws Exception {
		return cr.findByName(name);
	}

	@Override
	public void ajouterCompany(Company company) {
		cr.save(company);
	}
}
