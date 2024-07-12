package com.DiscountCalculatorApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                .httpBasic(withDefaults())  // Use the new way to configure HTTP Basic Authentication
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails employee = User.withUsername("employee")
                .password("{noop}password")
                .roles("EMPLOYEE")
                .build();

        UserDetails affiliate = User.withUsername("affiliate")
                .password("{noop}password")
                .roles("AFFILIATE")
                .build();

        UserDetails loyalCustomer = User.withUsername("loyal_customer")
                .password("{noop}password")
                .roles("LOYAL_CUSTOMER")
                .build();

        UserDetails customer = User.withUsername("customer")
                .password("{noop}password")
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(employee, affiliate, loyalCustomer, customer);
    }
}
