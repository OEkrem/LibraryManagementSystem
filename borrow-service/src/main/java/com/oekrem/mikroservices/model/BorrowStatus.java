package com.oekrem.mikroservices.model;

import lombok.Getter;

@Getter
public enum BorrowStatus {
    BORROWED("BORROWED"),
    OVERDUE("OVERDUE"),
    RETURNED("RETURNED"),
    LOST("LOST"),
    DAMAGED("DAMAGED"),;

    private final String displayName;

    BorrowStatus(String displayName) {
        this.displayName = displayName;
    }

    public static BorrowStatus fromString(String text) {
        for (BorrowStatus status : BorrowStatus.values()) {
            if (status.getDisplayName().equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }
}