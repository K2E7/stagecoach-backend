package com.capstone.stagecoach.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.capstone.stagecoach.model.Account;
import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findByUserId(String userId);
    Account findByAccountNumber(String accountNumber);
}
