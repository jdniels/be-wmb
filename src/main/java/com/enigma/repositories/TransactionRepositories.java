package com.enigma.repositories;

import com.enigma.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositories extends JpaRepository<Transaction, String> {
}
