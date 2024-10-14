package com.capstone.stagecoach.service;

import com.capstone.stagecoach.model.*;
import com.capstone.stagecoach.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction transferFunds(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        // Fetch the debtor's (from) account
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        if (fromAccount == null) {
            throw new RuntimeException("From account not found");
        }

        // Fetch the creditor's (to) account
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
        if (toAccount == null) {
            throw new RuntimeException("To account not found");
        }

        // Check if there are sufficient funds in the fromAccount
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Perform the transaction by updating balances
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        // Save the updated accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create a transaction record
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(fromAccount.getId());
        transaction.setToAccountId(toAccount.getId());
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setStatus("Completed");

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionHistory(String accountId) {
        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
    }

    public List<Transaction> getTransactionsByDateRange(Date startDate, Date endDate) {
        return transactionRepository.findByTransactionDateBetween(startDate, endDate);
    }
}