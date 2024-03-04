package com.jpcchaves.parkinglotapi.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private static final Logger logger = Logger.getLogger(JwtAuthorizationFilter.class.getName());

    public JwtAuthorizationFilter(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);

        if(token == null || token.startsWith(JwtUtils.JWT_BEARER)){
            logger.info("JWT esta nulo, vazio, ou tem um formato invalido!");
            filterChain.doFilter(request, response);
            return;
        }

        if (!JwtUtils.isTokenValid(token)) {
            logger.warning("JWT invalido ou expirado!");
            filterChain.doFilter(request, response);
            return;
        }

        String username = JwtUtils.getUsernameFromToken(token);

        toAuthentication(request, username);

        filterChain.doFilter(request, response);
    }

    private void toAuthentication(HttpServletRequest request, String username) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
                .authenticated(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource()
                        .buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
