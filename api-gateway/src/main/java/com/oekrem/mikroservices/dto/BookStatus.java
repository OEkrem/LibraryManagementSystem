package com.oekrem.mikroservices.dto;

import lombok.Getter;

@Getter
public enum BookStatus {
    BORROWED ("BORROWED"),
    AVAILABLE ("AVAILABLE"),
    LOST ("LOST");

    private final String displayName;

    BookStatus(String displayName) {
        this.displayName = displayName;
    }

    public static BookStatus fromString(String text) {
        for (BookStatus status : BookStatus.values()) {
            if (status.getDisplayName().equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }
}
