package DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import Model.Service;


public interface ServiceRepository extends JpaRepository <Service, Long> {

}
