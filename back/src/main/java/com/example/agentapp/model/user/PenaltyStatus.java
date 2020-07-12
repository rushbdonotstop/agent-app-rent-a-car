package com.example.agentapp.model.user;

public enum PenaltyStatus {
    NOT_PAID, PAID;

    public static PenaltyStatus toEnum(String penalty) {
        switch (penalty.toUpperCase()) {
            case "NOT_PAID":
                return NOT_PAID;
            case "PAID":
                return PAID;
            default:
                throw new IllegalArgumentException("Status " + penalty + " does not exist.");
        }
    }
}
