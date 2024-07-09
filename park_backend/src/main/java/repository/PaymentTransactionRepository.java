package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DBEntity.PaymentTransactions;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransactions, Long>{

}
