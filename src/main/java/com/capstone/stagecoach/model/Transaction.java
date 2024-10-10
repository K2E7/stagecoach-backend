package com.capstone.stagecoach.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stagecoach_transactions")
public class Transaction {

    @Id
    private String id;

    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;
    private Date transactionDate;
    private String status; // e.g., "Completed", "Pending", "Failed"
}
