package DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import Model.Grade;


public interface GradeRepository extends JpaRepository<Grade, Long>{

}
