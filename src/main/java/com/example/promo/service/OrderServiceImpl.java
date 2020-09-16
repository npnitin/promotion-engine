package com.example.promo.service;


import com.example.promo.bo.Order;
import com.example.promo.bo.Product;
import com.example.promo.bo.PromoType;
import com.example.promo.bo.Promotion;
import com.example.promo.utils.ComboStrategy;
import com.example.promo.utils.IndividualItemStrategy;
import com.example.promo.utils.PromotionStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderServiceImpl implements OrderService {
    static Map<String,Double> productPriceMap = new ConcurrentHashMap<>();
    static List<Promotion> promotions = new ArrayList<>();
    static{
        //products pricing and promotions data is hard coded and can be read from any sources
        productPriceMap.put("A",50D);
        productPriceMap.put("B",30D);
        productPriceMap.put("C",20D);
        productPriceMap.put("D",15D);
        Promotion individualPromotion = new Promotion();
        individualPromotion.setPromoType("IND");
        List<Product> products = new ArrayList<>();
        products.add(new Product("A",50D));
        individualPromotion.setProducts(products);
        individualPromotion.setPrice(130D);
        individualPromotion.setCount(3);

        Promotion individualPromotion1 = new Promotion();
        individualPromotion1.setPromoType("IND");
        List<Product> products1 = new ArrayList<>();
        products1.add(new Product("B",30D));
        individualPromotion1.setProducts(products1);
        individualPromotion1.setPrice(45D);
        individualPromotion1.setCount(2);

        Promotion comboPromotion = new Promotion();
        comboPromotion.setPromoType("COMBO");
        List<Product> productsCombo = new ArrayList<>();
        productsCombo.add(new Product("C",20D));
        productsCombo.add(new Product("D",15D));
        comboPromotion.setProducts(productsCombo);
        comboPromotion.setPrice(30);

        promotions.add(individualPromotion);
        promotions.add(individualPromotion1);
        promotions.add(comboPromotion);
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
    public double calculateTotalBill(Order order) {
        double discountedBill=0;
        PromotionStrategy promotionStrategy;
        for (Promotion promotion:promotions){
            if(promotion.getPromoType().equalsIgnoreCase(PromoType.IND.getDesc())){
                promotionStrategy = new IndividualItemStrategy();
                promotionStrategy.processPromotionStrategy(order,promotion);
            }else if(promotion.getPromoType().equalsIgnoreCase(PromoType.COMBO.getDesc())){
                promotionStrategy = new ComboStrategy();
                promotionStrategy.processPromotionStrategy(order,promotion);
            }
        }
        discountedBill = order.getDiscountedBill();
        //add remaining items which are not covered under any promotion
        for (Product product:order.getProducts().keySet()){
            discountedBill = discountedBill + (order.getProducts().get(product) * product.getPrice());
        }
        return discountedBill;
    }

    private List<Promotion> getPromotionsByType(List<Promotion> promotions, String type) {
        List<Promotion> promotionsByType = new CopyOnWriteArrayList<>();
        for (Promotion promotion:promotions){
            if(promotion.getPromoType().equalsIgnoreCase(type)){
                promotionsByType.add(promotion);
            }
        }
        return promotionsByType;
    }
}
