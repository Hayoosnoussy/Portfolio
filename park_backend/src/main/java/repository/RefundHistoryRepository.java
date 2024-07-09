package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DBEntity.RefundHistory;

public interface RefundHistoryRepository  extends JpaRepository <RefundHistory, Long> {

}
