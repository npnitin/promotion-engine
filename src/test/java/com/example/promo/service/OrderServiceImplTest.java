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
    @Test
    void calculateTotalBillAllItemsTest() {
        List<Product> products = new CopyOnWriteArrayList<>();
        products.add(new Product("A",50D));
        products.add(new Product("B",30D));
        products.add(new Product("C",20D));
        products.add(new Product("D",15D));
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.generateOrder(products);
        double totalBill = orderService.calculateTotalBill(order);
        assertEquals(totalBill,110D);
    }
    @Test
    void calculateTotalBillCOMBOPromotionTest() {
        List<Product> products = new CopyOnWriteArrayList<>();
        products.add(new Product("C",20D));
        products.add(new Product("D",15D));
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.generateOrder(products);
        double totalBill = orderService.calculateTotalBill(order);
        assertEquals(totalBill,30D);
    }
    @Test
    void calculateTotalBillINDPromotionTest() {
        List<Product> products = new CopyOnWriteArrayList<>();
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.generateOrder(products);
        double totalBill = orderService.calculateTotalBill(order);
        assertEquals(totalBill,175D);
    }

    @Test
    void calculateBillScenarioATest() {
        List<Product> products = new CopyOnWriteArrayList<>();
        products.add(new Product("A",50D));
        products.add(new Product("B",30D));
        products.add(new Product("C",20D));
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.generateOrder(products);
        double totalBill = orderService.calculateTotalBill(order);
        assertEquals(totalBill,100D);
    }
    @Test
    void calculateBillScenarioBTest() {
        List<Product> products = new CopyOnWriteArrayList<>();
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        products.add(new Product("C",20D));
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.generateOrder(products);
        double totalBill = orderService.calculateTotalBill(order);
        assertEquals(totalBill,370D);
    }
    @Test
    void calculateBillScenarioCTest() {
        List<Product> products = new CopyOnWriteArrayList<>();
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("A",50D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        products.add(new Product("B",30D));
        products.add(new Product("C",20D));
        products.add(new Product("D",15D));
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.generateOrder(products);
        double totalBill = orderService.calculateTotalBill(order);
        assertEquals(totalBill,280D);
    }
}