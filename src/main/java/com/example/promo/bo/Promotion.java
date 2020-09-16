package com.example.promo.bo;

import java.util.List;

public class Promotion {
    List<Product> products;
    double price;
    int count;
    String promoType;

    public Promotion(List<Product> products, double price, int count, String promoType) {
        this.products = products;
        this.price = price;
        this.count = count;
        this.promoType = promoType;
    }

    public Promotion() {

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public String getPromoType() {
        return promoType;
    }
}
