package com.example.promo.test;

import com.example.promo.bo.Order;
import com.example.promo.bo.Product;
import com.example.promo.service.OrderService;
import com.example.promo.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of items:");
        int items = scanner.nextInt();
        List<String> products = new ArrayList<>();
        for(int i=0;i<items;i++){
            System.out.println("Enter type of product(A,B,C,D):");
            products.add(scanner.next().toUpperCase());
        }
        OrderService orderService = new OrderServiceImpl();
        System.out.println(products);
        List<Product> productList = orderService.getOrderList(products);
        System.out.println(productList);
        Order order = orderService.generateOrder(productList);
        System.out.println(order);
    }
}
