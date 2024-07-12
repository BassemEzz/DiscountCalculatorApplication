package com.DiscountCalculatorApplication;

import com.DiscountCalculatorApplication.model.Bill;
import com.DiscountCalculatorApplication.model.Item;
import com.DiscountCalculatorApplication.model.Role;
import com.DiscountCalculatorApplication.model.Users;
import com.DiscountCalculatorApplication.service.DiscountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    public DiscountServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateNetPayableAmountForEmployee() {
        Users users = new Users();
        users.setRoles(new HashSet<>(Set.of(new Role(Role.RoleType.EMPLOYEE))));

        Bill bill = new Bill();
        bill.setItems(Arrays.asList(
                new Item("item1", 200, false),
                new Item("item2", 300, true),
                new Item("item3", 500, false)
        ));

        double result = discountService.calculateNetPayableAmount(bill, users);
        double expected = 755.0;
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculateNetPayableAmountForAffiliate() {
        Users user = new Users();
        user.setRoles(new HashSet<>(Set.of(new Role(Role.RoleType.AFFILIATE))));

        Bill bill = new Bill();
        bill.setItems(Arrays.asList(
                new Item("item1", 200, false),
                new Item("item2", 300, true),
                new Item("item3", 500, false)
        ));

        double result = discountService.calculateNetPayableAmount(bill, user);

        // Calculation:
        // Non-grocery total: 200 + 500 = 700
        // 10% discount on non-grocery: 0.1 * 700 = 70
        // Remaining amount after discount: 1000 - 70 = 930
        // Additional discount for every $100: 9 * 5 = 45
        // Final amount: 930 - 45 = 885
        double expected = 885.0;
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculateNetPayableAmountForLoyalCustomer() {
        Users user = new Users();
        user.setRoles(new HashSet<>(Set.of(new Role(Role.RoleType.LOYAL_CUSTOMER))));

        Bill bill = new Bill();
        bill.setItems(Arrays.asList(
                new Item("item1", 200, false),
                new Item("item2", 300, true),
                new Item("item3", 500, false)
        ));

        double result = discountService.calculateNetPayableAmount(bill, user);

        // Calculation:
        // Non-grocery total: 200 + 500 = 700
        // 5% discount on non-grocery: 0.05 * 700 = 35
        // Remaining amount after discount: 1000 - 35 = 965
        // Additional discount for every $100: 9 * 5 = 45
        // Final amount: 965 - 45 = 920
        double expected = 920.0;
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculateNetPayableAmountForCustomer() {
        Users user = new Users();
        user.setRoles(new HashSet<>(Set.of(new Role(Role.RoleType.CUSTOMER))));

        Bill bill = new Bill();
        bill.setItems(Arrays.asList(
                new Item("item1", 200, false),
                new Item("item2", 300, true),
                new Item("item3", 500, false)
        ));

        double result = discountService.calculateNetPayableAmount(bill, user);

        // Calculation:
        // No percentage discount
        // Additional discount for every $100: 10 * 5 = 50
        // Final amount: 1000 - 50 = 950
        double expected = 950.0;
        assertEquals(expected, result, 0.001);
    }
}