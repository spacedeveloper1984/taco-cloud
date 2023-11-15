package com.spring.in.action.tacocloud.security;

import com.spring.in.action.tacocloud.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder encoder) {
        return new User(username, encoder.encode(password), fullName, street, city, state, zip, phone);
    }
}
