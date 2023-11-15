package com.spring.in.action.tacocloud.controller;

import com.spring.in.action.tacocloud.dao.UserRepository;
import com.spring.in.action.tacocloud.security.RegistrationForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepository.save(form.toUser(encoder));
        return "redirect:/login";
    }
}
