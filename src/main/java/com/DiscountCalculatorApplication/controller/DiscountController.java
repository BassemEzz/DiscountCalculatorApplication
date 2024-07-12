package com.DiscountCalculatorApplication.controller;

import com.DiscountCalculatorApplication.model.Bill;
import com.DiscountCalculatorApplication.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.DiscountCalculatorApplication.service.DiscountService;
import com.DiscountCalculatorApplication.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private UserService userService;

    @PostMapping("/calculate")
    public double calculateDiscount(@RequestBody Bill bill, Principal principal) {
        Users users = userService.findByUsername(principal.getName());
        return discountService.calculateNetPayableAmount(bill, users);
    }
}