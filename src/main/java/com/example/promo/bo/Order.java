package com.example.promo.bo;

import java.util.Map;

public class Order {
    Map<Product,Integer> products;
    private double discountedBill;

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }


    public double getDiscountedBill() {
        return discountedBill;
    }

    public void setDiscountedBill(double discountedBill) {
        this.discountedBill = discountedBill;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                '}';
    }
}

