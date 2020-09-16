package com.example.promo.utils;

import com.example.promo.bo.Order;
import com.example.promo.bo.Product;
import com.example.promo.bo.PromoType;
import com.example.promo.bo.Promotion;

import java.util.List;

public class IndividualItemStrategy implements PromotionStrategy {
    @Override
    public void processPromotionStrategy(Order order, Promotion promotion) {
            if(promotion.getPromoType().equalsIgnoreCase(PromoType.IND.getDesc())){
                Product product = promotion.getProducts().get(0);
                if(!order.getProducts().containsKey(product)){
                    return;
                }
                int itemCount = order.getProducts().get(product);
                while(itemCount>0){
                    if(order.getProducts().containsKey(product) && itemCount >= promotion.getCount()){
                        order.setDiscountedBill(order.getDiscountedBill()+promotion.getPrice());
                        itemCount = itemCount - promotion.getCount();
                    }else if(itemCount>0){
                        order.setDiscountedBill(order.getDiscountedBill()+(product.getPrice()*itemCount));
                        itemCount=0;
                        order.getProducts().remove(product);
                    }
                }
                if(itemCount==0){
                    order.getProducts().remove(product);
                }
            }
    }
}
