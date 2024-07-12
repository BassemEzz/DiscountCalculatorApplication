package com.DiscountCalculatorApplication.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Bill {
    private List<Item> items;
}

