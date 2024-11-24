package com.kaansenada.loans.enums;

public enum LoanEnum {
    HOME_LOAN("Home Loan"),
    STATUS_201("201"),
    STATUS_200("200"),
    STATUS_417("417"),
    MESSAGE_201("Loan Created Successfully"),
    MESSAGE_200("Request Processed Successfully"),
    MESSAGE_417_UPDATE("Update failed please try again later"),
    MESSAGE_417_DELETE("Loan Deletion failed please try again later");

    public final String response;
    public static final int NEW_LOAN_LIMIT =100_000;

    LoanEnum(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return response;
    }
}
