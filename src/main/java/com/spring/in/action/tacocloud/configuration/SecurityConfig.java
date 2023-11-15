package com.spring.in.action.tacocloud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(new User("user1", encoder.encode("password"), Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        )));

        userDetailsList.add(new User("user2", encoder.encode("password"), Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        )));

        return new InMemoryUserDetailsManager(userDetailsList);
    }
}
