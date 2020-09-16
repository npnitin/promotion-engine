package com.example.promo.service;

import com.example.promo.bo.Order;
import com.example.promo.bo.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void getOrderList() {
        OrderService orderService = new OrderServiceImpl();
        List<String> items = new ArrayList<>();
        items.add("A");
        List<Product> products = orderService.getOrderList(items);
        assertEquals(products.size(),items.size());
    }

    @Test
    void generateOrder() {
        List<Product> products = new CopyOnWriteArrayList<>();
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("B",30D));
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.generateOrder(products);
        assertEquals(order.getProducts().size(),2);
        assertEquals(order.getProducts().get(new Product("A",50D)),2);
    }
}