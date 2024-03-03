package com.jpcchaves.parkinglotapi.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;

public class JwtUserDetails extends User {
    @Serial
    private static final long serialVersionUID = -4282341358282149231L;
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
