package com.enigma.repositories;

import com.enigma.entity.TableEntities;
import com.enigma.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositories extends JpaRepository<Transaction, String> {
    Transaction getTransactionByTableEntitiesAndPaymentStatus(TableEntities tableEntities, String paymentStatus);
}
