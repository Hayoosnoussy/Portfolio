package tn.globebusiness.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Profession;
import tn.globebusiness.spring.Repositories.ProfessionRepository;

@Service
public class ProfessionServiceImpl implements IProfessionService{
    @Autowired
    ProfessionRepository pr;

    @Override
    public Profession addProfession(Profession p) {
        return pr.save(p);
    }

    @Override
    public Profession updateProfession(String name, Profession p) throws Exception {
        Profession profession = pr.findProfessionByName(name);
        if(profession!=null){
            profession.setName(p.getName());
        } else {
            throw new Exception("Profession Not found");
        }
        return pr.save(profession);
    }

    @Override
    public void deleteProfession(String name){
            pr.deleteProfessionByName(name);
    }

}
