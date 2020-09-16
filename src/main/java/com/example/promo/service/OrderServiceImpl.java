package com.example.promo.service;


import com.example.promo.bo.Order;
import com.example.promo.bo.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderServiceImpl implements OrderService {
    static Map<String,Double> productPriceMap = new ConcurrentHashMap<>();
    static{
        productPriceMap.put("A",50D);
        productPriceMap.put("B",30D);
        productPriceMap.put("C",20D);
        productPriceMap.put("D",15D);
    }

    @Override
    public List<Product> getOrderList(List<String> items) {
        List<Product> orderProducts = new ArrayList<>();
        for(String product:items){
            orderProducts.add(new Product(product,productPriceMap.get(product)));
        }
        return orderProducts;
    }

    @Override
    public Order generateOrder(List<Product> productItems) {
        Map<Product,Integer> products = new HashMap<>();
        Order order = new Order();
        for (Product product:productItems){
            if(products.containsKey(product)){
                products.put(product,products.get(product)+1);
            }else{
                products.put(product,1);
            }
        }
        order.setProducts(products);
        return order;
    }

    @Override
    public double calculateTotalBill(Order Order) {
        return 0;
    }
}
