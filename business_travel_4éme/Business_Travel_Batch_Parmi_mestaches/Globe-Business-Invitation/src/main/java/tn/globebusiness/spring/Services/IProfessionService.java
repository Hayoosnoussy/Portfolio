package tn.globebusiness.spring.Services;

import tn.globebusiness.spring.Entities.Profession;

public interface IProfessionService {
    public Profession addProfession(Profession p);
    public Profession updateProfession (String name, Profession p) throws Exception;
    public void deleteProfession(String name);
}
