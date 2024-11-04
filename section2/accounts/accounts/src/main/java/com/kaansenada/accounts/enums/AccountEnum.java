package com.kaansenada.accounts.enums;

public enum AccountEnum {

    SAVINGS("Savings"),
    ADDRESS("Address"),
    STATUS_201("201"),
    MESSAGE_201("Account Created Successfully"),
    STATUS_200("200"),
    MESSAGE_200("Request Processed Successfully"),
    STATUS_500("500"),
    MESSAGE_500("Internal Server Error, Please Try Again Later");

    public final String response;

    AccountEnum(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return response;
    }
}

