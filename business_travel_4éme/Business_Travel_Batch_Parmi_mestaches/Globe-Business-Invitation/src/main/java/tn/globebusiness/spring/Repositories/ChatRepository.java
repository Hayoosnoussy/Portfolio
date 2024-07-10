package tn.globebusiness.spring.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.globebusiness.spring.Entities.Chat;
import tn.globebusiness.spring.Entities.Travel;

@Repository
public interface ChatRepository extends CrudRepository<Chat,Long> {
	@Query("select c.message, c.dateChat from Chat c where c.travel=:travel order by c.dateChat ")
	List<String> getDiscussion(@Param("travel") Travel travel);
}
