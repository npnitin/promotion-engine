package com.example.promo.utils;

import com.example.promo.bo.Order;
import com.example.promo.bo.Product;
import com.example.promo.bo.PromoType;
import com.example.promo.bo.Promotion;

import java.util.List;
import java.util.Set;

public class ComboStrategy implements PromotionStrategy {
    @Override
    public void processPromotionStrategy(Order order, Promotion promotion) {
            if(promotion.getPromoType().equalsIgnoreCase(PromoType.COMBO.getDesc())){
                List<Product> productsInPromo = promotion.getProducts();
                Set<Product> productsInOrder = order.getProducts().keySet();
                    if(allProductsAvailable(productsInOrder,productsInPromo)){
                        order.setDiscountedBill(order.getDiscountedBill()+promotion.getPrice());
                        for (Product product:productsInPromo){
                            order.getProducts().put(product,order.getProducts().get(product)-1);
                            if(order.getProducts().get(product)==0){
                                order.getProducts().remove(product);
                            }
                        }
                    }
                }
    }

    private boolean allProductsAvailable(Set<Product> productsInOrder, List<Product> productsInPromo) {
        for(Product product:productsInPromo){
            if(!productsInOrder.contains(product)){
                return false;
            }
        }
        return true;
    }
}
