package com.capstone.stagecoach.payload.request;

public class AccountRequest {
    private String accountType;

    // Constructors
    public AccountRequest() {}

    public AccountRequest(String accountType) {
        this.accountType = accountType;
    }

    // Getter and Setter
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
