package com.oekrem.mikroservices.model;

public enum BorrowStatus {
    AVAILABLE("AVAILABLE"),
    LOST("LOST"),
    BORROWED("BORROWED"),
    EXPIRED("EXPIRED");

    private final String displayName;

    BorrowStatus(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
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