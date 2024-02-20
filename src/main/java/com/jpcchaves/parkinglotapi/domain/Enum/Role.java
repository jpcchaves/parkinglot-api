package com.jpcchaves.parkinglotapi.domain.Enum;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return removeRolePrefix(role);
    }

    private String removeRolePrefix(String role) {
        final String ROLE_PREFIX = "ROLE_";
        return role.substring(ROLE_PREFIX.length());
    }
}
