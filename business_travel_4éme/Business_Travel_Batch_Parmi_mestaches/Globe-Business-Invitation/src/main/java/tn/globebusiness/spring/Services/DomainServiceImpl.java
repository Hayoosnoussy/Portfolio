package tn.globebusiness.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Domain;
import tn.globebusiness.spring.Repositories.DomainRepository;

@Service
public class DomainServiceImpl implements IDomainService {
    @Autowired
    DomainRepository dr;

    @Override
    public Domain addDomain(Domain domain) {
        return dr.save(domain);
    }

    @Override
    public Domain updateDomain(String name, Domain domain) {
        Domain updatedDomain = dr.findDomainByName(name);
        if(updatedDomain!=null){
            updatedDomain.setName(domain.getName());
        }
        return dr.save(updatedDomain);
    }

    @Override
    public void deleteDomain(String Name) {
        dr.deleteDomainByName(Name);
    }
}
