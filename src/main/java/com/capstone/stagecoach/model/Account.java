package com.capstone.stagecoach.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stagecoach_accounts")
public class Account {

    @Id
    private String id;

    private String userId;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType; // e.g., "Checking", "Savings"
}
