package com.example.realEstateManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("deprecation")
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String sql = "SELECT * FROM users WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) ->
                User.builder()
                    .username(rs.getString("email"))   // login feito com email
                    .password(rs.getString("password")) // já vem encriptada
                    .roles("USER")                     // podes criar uma coluna "role" se quiseres
                    .build()
            );
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("Utilizador não encontrado com email: " + email);
        }
    }
}