package com.example.realEstateManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT email, password FROM users WHERE email = ?",
                (rs, rowNum) -> org.springframework.security.core.userdetails.User
                    .withUsername(rs.getString("email"))
                    .password(rs.getString("password"))
                    .roles("USER") // por enquanto todos são USER
                    .build(),
                email
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("Utilizador não encontrado: " + email);
        }
    }
}
