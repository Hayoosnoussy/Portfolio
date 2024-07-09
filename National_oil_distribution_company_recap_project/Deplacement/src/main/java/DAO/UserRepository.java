package DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
 public List<User> findByNom(String n);
 public Page<User> findByNom(String n,Pageable pageable);
public Optional<User> findByLogin(String login);
public Iterable<User> findByPwd(String pwd);


}
