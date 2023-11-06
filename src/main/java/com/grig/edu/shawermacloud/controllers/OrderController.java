package com.grig.edu.shawermacloud.controllers;

import com.grig.edu.shawermacloud.models.ShawermaOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/current")
    public String processOrder() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(ShawermaOrder order, SessionStatus sessionStatus) {
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/success";
    }
}
