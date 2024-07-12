package com.DiscountCalculatorApplication.repository;

import com.DiscountCalculatorApplication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
