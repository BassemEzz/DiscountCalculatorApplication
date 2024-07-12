package com.DiscountCalculatorApplication.service;


import com.DiscountCalculatorApplication.model.Bill;
import com.DiscountCalculatorApplication.model.Users;
import com.DiscountCalculatorApplication.model.Item;
import com.DiscountCalculatorApplication.model.Role;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public double calculateNetPayableAmount(Bill bill, Users users) {
        double totalAmount = bill.getItems().stream().mapToDouble(Item::getPrice).sum();
        double groceryAmount = bill.getItems().stream().filter(Item::isGrocery).mapToDouble(Item::getPrice).sum();
        double nonGroceryAmount = totalAmount - groceryAmount;

        double discount = 0;

        if (users.getRoles().stream().anyMatch(role -> role.getName() == Role.RoleType.EMPLOYEE)) {
            discount = 0.30 * nonGroceryAmount;
        } else if (users.getRoles().stream().anyMatch(role -> role.getName() == Role.RoleType.AFFILIATE)) {
            discount = 0.10 * nonGroceryAmount;
        } else if (users.getRoles().stream().anyMatch(role -> role.getName() == Role.RoleType.LOYAL_CUSTOMER)) {
            discount = 0.05 * nonGroceryAmount;
        }

        double finalAmount = totalAmount - discount;

        int hundreds = (int) (finalAmount / 100);
        double additionalDiscount = hundreds * 5;

        return finalAmount - additionalDiscount;
    }
}

