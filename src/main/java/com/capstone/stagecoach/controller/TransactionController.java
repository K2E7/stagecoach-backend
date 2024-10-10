package com.capstone.stagecoach.controller;

import com.capstone.stagecoach.model.Transaction;
import com.capstone.stagecoach.payload.request.TransferRequest;
import com.capstone.stagecoach.service.TransactionService;
import com.capstone.stagecoach.service.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transferFunds(@RequestBody TransferRequest transferRequest) {
        Transaction transaction = transactionService.transferFunds(
            transferRequest.getFromAccountNumber(),
            transferRequest.getToAccountNumber(),
            transferRequest.getAmount()
        );
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getTransactionHistory(Authentication authentication) {
        String userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        List<Transaction> transactions = transactionService.getTransactionHistory(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/report")
    public ResponseEntity<?> getTransactionsReport(@RequestParam String startDate, @RequestParam String endDate) {
        // Implement date parsing and call to service
        // Return PDF report as ResponseEntity<byte[]>
        return ResponseEntity.ok().build();
    }
}

