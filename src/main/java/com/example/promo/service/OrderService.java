package com.example.promo.service;

import com.example.promo.bo.Order;
import com.example.promo.bo.Product;

import java.util.List;

public interface OrderService {
    List<Product> getOrderList(List<String> items);
    Order generateOrder(List<Product> products);
}
