package com.capstone.stagecoach.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.capstone.stagecoach.model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByFromAccountIdOrToAccountId(String fromAccountId, String toAccountId);
    List<Transaction> findByTransactionDateBetween(Date startDate, Date endDate);
}
