package com.jpcchaves.parkinglotapi.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {
    private com.jpcchaves.parkinglotapi.domain.models.User user;

    public JwtUserDetails(com.jpcchaves.parkinglotapi.domain.models.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getRole() {
        return user.getRole().name();
    }
}
