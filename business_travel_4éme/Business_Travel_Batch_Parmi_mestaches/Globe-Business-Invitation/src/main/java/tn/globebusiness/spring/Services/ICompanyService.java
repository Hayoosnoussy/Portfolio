package tn.globebusiness.spring.Services;

import tn.globebusiness.spring.Entities.Company;

public interface ICompanyService {
    public Company UpdateCompany(String companyName, Company company) throws Exception;

    public Company DisplayCompanyData(String name) throws Exception;
}
