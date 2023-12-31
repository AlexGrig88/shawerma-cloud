package com.grig.edu.shawermacloud.controllers;

import com.grig.edu.shawermacloud.models.ShawermaOrder;
import com.grig.edu.shawermacloud.repositories.order.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("orders")
@SessionAttributes("shawermaOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(@Qualifier("jdbcOrderRepository") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String processOrder() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid ShawermaOrder order,
                               Errors errors,
                               SessionStatus sessionStatus){
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/success";
    }
}
