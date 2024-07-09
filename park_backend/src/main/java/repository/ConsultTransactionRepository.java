package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DBEntity.ConsultTransaction;

public interface ConsultTransactionRepository  extends JpaRepository<ConsultTransaction, Long> { 
	
	public ConsultTransaction findOneByTransactionIdOrderByIdDesc(String transactionId);

}
