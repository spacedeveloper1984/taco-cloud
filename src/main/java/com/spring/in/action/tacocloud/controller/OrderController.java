package com.spring.in.action.tacocloud.controller;

import com.spring.in.action.tacocloud.dao.OrderRepository;
import com.spring.in.action.tacocloud.dao.UserRepository;
import com.spring.in.action.tacocloud.model.TacoOrder;
import com.spring.in.action.tacocloud.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid TacoOrder order,
            Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @PreAuthorize("hasRole(`ADMIN`)")
    @PostMapping("/delete/orders/all")
    public String deleteAllOrders() {
        orderRepository.deleteAll();
        return "redirect:/admin";
    }
}
