package com.capstone.stagecoach.controller;

import com.capstone.stagecoach.model.Account;
import com.capstone.stagecoach.payload.request.AccountRequest;
import com.capstone.stagecoach.service.AccountService;
import com.capstone.stagecoach.service.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<?> getUserAccounts(Authentication authentication) {
        String userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        List<Account> accounts = accountService.getAccountsByUserId(userId);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/")
    public ResponseEntity<?> createAccount(Authentication authentication, @RequestBody AccountRequest accountRequest) {
        String userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        Account account = accountService.createAccount(userId, accountRequest.getAccountType());
        return ResponseEntity.ok(account);
    }
}

