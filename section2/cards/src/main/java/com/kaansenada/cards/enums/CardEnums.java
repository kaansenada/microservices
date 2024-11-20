package com.kaansenada.cards.enums;

public enum CardEnums {
    CREDIT_CARD("Credit Card"),
    STATUS_201("201"),
    STATUS_200("200"),
    STATUS_417("417"),
    MESSAGE_201("Card Created Successfully"),
    MESSAGE_200("Request Processed Successfully"),
    MESSAGE_417_UPDATE("Update failed please try again later"),
    MESSAGE_417_DELETE("Card Deletion failed please try again later"),;

    public final String response;
    public static final int NEW_CARD_LIMIT =100_000;

    CardEnums(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return response;
    }
}
