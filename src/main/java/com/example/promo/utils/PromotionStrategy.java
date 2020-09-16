package com.example.promo.utils;

import com.example.promo.bo.Order;
import com.example.promo.bo.Promotion;

import java.util.List;

public interface PromotionStrategy {

    public void processPromotionStrategy(Order order, Promotion promotion);
}
