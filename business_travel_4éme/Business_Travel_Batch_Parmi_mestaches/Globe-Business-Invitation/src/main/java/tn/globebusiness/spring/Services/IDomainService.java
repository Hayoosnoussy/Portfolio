package tn.globebusiness.spring.Services;

import tn.globebusiness.spring.Entities.Domain;

public interface IDomainService {
    Domain addDomain(Domain domain);
    Domain updateDomain(String name, Domain domain);
    void deleteDomain(String Name);
}
