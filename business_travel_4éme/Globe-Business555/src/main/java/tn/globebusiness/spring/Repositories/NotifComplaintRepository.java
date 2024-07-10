package tn.globebusiness.spring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.NotifComplaint;

@Repository
public interface NotifComplaintRepository extends CrudRepository<NotifComplaint, Long> {

}
