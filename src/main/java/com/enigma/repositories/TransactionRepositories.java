package com.enigma.repositories;

import com.enigma.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositories extends JpaRepository<Transaction, String> {
}
