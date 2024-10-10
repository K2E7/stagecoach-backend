package com.capstone.stagecoach.service;

import com.capstone.stagecoach.model.Account;
import com.capstone.stagecoach.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String userId, String accountType) {
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountType(accountType);
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public List<Account> getAccountsByUserId(String userId) {
        return accountRepository.findByUserId(userId);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    private String generateAccountNumber() {
        // Implement account number generation logic
        return UUID.randomUUID().toString();
    }

    // Additional methods as needed
}
